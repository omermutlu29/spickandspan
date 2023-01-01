package com.spickandspan.user.product.domain.mapper;

import com.spickandspan.user.product.domain.dto.req.CreateProductRequest;
import com.spickandspan.user.product.domain.dto.res.ProductResponseDTO;
import com.spickandspan.user.product.domain.entity.ProductEntity;
import com.spickandspan.user.user.domain.mapper.UserMapper;


public class ProductMapper {
    public static ProductResponseDTO convertToResponseDTO(ProductEntity productEntity) {
        return ProductResponseDTO.builder()
                .name(productEntity.getName())
                .description(productEntity.getDescription())
                .price(productEntity.getPrice())
                .build();
    }

    public static ProductEntity convertDtoToEntity(CreateProductRequest createProductRequest) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(createProductRequest.getName());
        productEntity.setPrice(createProductRequest.getPrice());
        return productEntity;
    }
}
