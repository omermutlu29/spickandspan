package com.spickandspan.productservice.product.domain.mapper;

import com.spickandspan.productservice.product.domain.dto.req.CreateProductRequest;
import com.spickandspan.productservice.product.domain.dto.res.ProductResponseDTO;
import com.spickandspan.productservice.product.domain.entity.ProductEntity;


public class ProductMapper {
    public static ProductResponseDTO convertToResponseDTO(ProductEntity productEntity) {
        return ProductResponseDTO.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .description(productEntity.getDescription())
                .price(productEntity.getPrice())
                .build();
    }

    public static ProductEntity convertDtoToEntity(CreateProductRequest createProductRequest) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(createProductRequest.getName());
        productEntity.setDescription(createProductRequest.getDescription());
        productEntity.setPrice(createProductRequest.getPrice());
        return productEntity;
    }
}
