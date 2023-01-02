package com.spickandspan.productservice.product.application.service;

import com.spickandspan.productservice.product.domain.dto.req.CreateProductRequest;
import com.spickandspan.productservice.product.domain.dto.req.UpdateProductRequest;
import com.spickandspan.productservice.product.domain.dto.res.ProductResponseDTO;

import java.util.List;

public interface IProductService {
    List<ProductResponseDTO> getAll();
    ProductResponseDTO create(CreateProductRequest createProductRequest, String userId);
    ProductResponseDTO getById(Long id);
    ProductResponseDTO update(String userKey,Long productId, UpdateProductRequest updateProductRequest);
    void deleteProduct(String userKey,Long id);
}
