package com.spickandspan.productservice.product.application.service;

import com.spickandspan.productservice.common.exception.EntityNotFoundException;
import com.spickandspan.productservice.common.exception.PermissionDeniedException;
import com.spickandspan.productservice.product.application.event.Event;
import com.spickandspan.productservice.product.application.event.EventType;
import com.spickandspan.productservice.product.application.event.ProductEvent;
import com.spickandspan.productservice.product.domain.dto.req.CreateProductRequest;
import com.spickandspan.productservice.product.domain.dto.req.UpdateProductRequest;
import com.spickandspan.productservice.product.domain.dto.res.ProductResponseDTO;
import com.spickandspan.productservice.product.domain.entity.ProductEntity;
import com.spickandspan.productservice.product.domain.mapper.ProductMapper;
import com.spickandspan.productservice.product.infrastructure.repository.ProductRepository;
import com.spickandspan.productservice.user.application.service.UserService;
import com.spickandspan.productservice.user.domain.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final UserService userService;
    private final KafkaTemplate<String, Event> kafkaTemplate;

    public ProductService(ProductRepository productRepository, UserService userService, KafkaTemplate<String, Event> kafkaTemplate) {
        this.productRepository = productRepository;
        this.userService = userService;
        this.kafkaTemplate = kafkaTemplate;
    }

    public List<ProductResponseDTO> getAll() {
        Pageable firstPageWithTwoElements = PageRequest.of(0, 2);
        return productRepository.findAll(firstPageWithTwoElements).stream().map(ProductMapper::convertToResponseDTO).toList();
    }

    public Page<ProductResponseDTO> getAll(Integer page) {
        page = pageControl(page);
        Pageable firstPageWithTwoElements = PageRequest.of(page, 5);
        return new PageImpl<>(productRepository.findAll(firstPageWithTwoElements).getContent().stream().map(ProductMapper::convertToResponseDTO).collect(Collectors.toList()));
    }

    @Transactional
    public ProductResponseDTO create(CreateProductRequest createProductRequest, String userId) {
        UserEntity userEntity = userService.findByKeycloakId(userId);
        ProductEntity productEntity = ProductMapper.convertDtoToEntity(createProductRequest);
        productEntity.setUser(userEntity);
        ProductEntity newProduct = this.productRepository.save(productEntity);

        kafkaTemplate.send("productActionTopic", new ProductEvent(newProduct.getId(), newProduct.getPrice(), EventType.INSERT));

        return ProductMapper.convertToResponseDTO(newProduct);
    }


    @Transactional
    public ProductResponseDTO update(String userKey, Long productId, UpdateProductRequest updateProductRequest) {
        ProductEntity productEntity = findById(productId);

        permissionToChangeProduct(userKey, productEntity);

        productEntity.setPrice(updateProductRequest.getPrice());
        productEntity.setDescription(updateProductRequest.getDescription());
        ProductEntity saved = this.productRepository.save(productEntity);

        kafkaTemplate.send("productActionTopic", new ProductEvent(saved.getId(), saved.getPrice(), EventType.UPDATE));

        return ProductMapper.convertToResponseDTO(saved);
    }

    public ProductResponseDTO getById(Long id) {
        return ProductMapper.convertToResponseDTO(this.findById(id));
    }

    @Transactional
    public void deleteProduct(String userKey, Long id) {
        ProductEntity productEntity = findById(id);
        permissionToChangeProduct(userKey, productEntity);
        this.productRepository.delete(productEntity);
        this.kafkaTemplate.send("productActionTopic", new ProductEvent(id, null, EventType.DELETE));
    }

    private ProductEntity findById(Long id) {
        return this.productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    private Integer pageControl(Integer page) {
        if ((page == null || page == 1) || page < 0) {
            page = 0;
        } else {
            page = page - 1;
        }
        return page;
    }

    private void permissionToChangeProduct(String userKey, ProductEntity productEntity) {
        UserEntity userEntity = userService.findByKeycloakId(userKey);
        if (!Objects.equals(userEntity.getId(), productEntity.getUser().getId())) {
            throw new PermissionDeniedException("You have no permission to update this product, you can only update your own products");
        }
    }
}
