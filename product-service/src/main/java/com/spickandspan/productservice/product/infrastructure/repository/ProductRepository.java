package com.spickandspan.productservice.product.infrastructure.repository;

import com.spickandspan.productservice.product.domain.entity.ProductEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<ProductEntity, Long> {
}
