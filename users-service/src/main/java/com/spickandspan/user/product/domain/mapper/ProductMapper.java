package com.spickandspan.user.product.domain.mapper;

import com.spickandspan.user.product.domain.dto.req.CreateProductRequest;
import com.spickandspan.user.product.domain.dto.res.ProductResponseDTO;
import com.spickandspan.user.product.domain.entity.ProductEntity;


public class ProductMapper {
    private static ProductResponseDTO convertToResponseDTO(ProductEntity productEntity) {
        return new ProductResponseDTO();
    }

    private static ProductEntity convertDtoToEntity(CreateProductRequest createProductRequest) {
        return new ProductEntity();
    }
}
