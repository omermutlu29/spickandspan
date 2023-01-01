package com.spickandspan.user.product.application.service;

import com.spickandspan.user.common.exception.EntityNotFoundException;
import com.spickandspan.user.product.domain.dto.req.CreateProductRequest;
import com.spickandspan.user.product.domain.dto.req.UpdateProductRequest;
import com.spickandspan.user.product.domain.dto.res.ProductResponseDTO;
import com.spickandspan.user.product.domain.entity.ProductEntity;
import com.spickandspan.user.product.domain.mapper.ProductMapper;
import com.spickandspan.user.product.infrastructure.repository.ProductRepository;
import com.spickandspan.user.user.application.service.UserService;
import com.spickandspan.user.user.domain.entity.UserEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final UserService userService;

    public ProductService(ProductRepository productRepository, UserService userService) {
        this.productRepository = productRepository;
        this.userService = userService;
    }

    public List<ProductResponseDTO> getAll() {
        return this.productRepository.findAll()
                .stream()
                .map(ProductMapper::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public ProductResponseDTO create(CreateProductRequest createProductRequest, String userId) {
        UserEntity userEntity = userService.findByKeycloakId(userId);
        ProductEntity productEntity = ProductMapper.convertDtoToEntity(createProductRequest);
        productEntity.setUser(userEntity);
        return ProductMapper.convertToResponseDTO(this.productRepository.save(productEntity));
    }

    public ProductResponseDTO getById(Long id) {
        return ProductMapper.convertToResponseDTO(this.findById(id));
    }

    public ProductResponseDTO update(Long productId, UpdateProductRequest updateProductRequest) {
        ProductEntity productEntity = findById(productId);
        productEntity.setPrice(updateProductRequest.getPrice());
        productEntity.setDescription(updateProductRequest.getDescription());
        return ProductMapper.convertToResponseDTO(this.productRepository.save(productEntity));
    }

    //todo eğer kullanıcı kendi ürününü silmiyorsa hata dönmeli 403
    public void deleteProduct(Long id) {
        this.productRepository.delete(this.findById(id));
    }

    private ProductEntity findById(Long id) {
        return this.productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
