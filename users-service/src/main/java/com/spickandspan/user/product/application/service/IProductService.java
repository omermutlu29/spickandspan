package com.spickandspan.user.product.application.service;

import com.spickandspan.user.product.domain.dto.req.CreateProductRequest;
import com.spickandspan.user.product.domain.dto.req.UpdateProductRequest;
import com.spickandspan.user.product.domain.dto.res.ProductResponseDTO;
import com.spickandspan.user.product.domain.entity.ProductEntity;

import java.util.List;

public interface IProductService {
    List<ProductResponseDTO> getAll();
    ProductResponseDTO create(CreateProductRequest createProductRequest, String userId);
    ProductResponseDTO getById(Long id);
    ProductResponseDTO update(Long productId, UpdateProductRequest updateProductRequest);
    void deleteProduct(Long id);
}
