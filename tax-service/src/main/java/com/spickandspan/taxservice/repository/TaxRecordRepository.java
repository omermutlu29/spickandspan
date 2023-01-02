package com.spickandspan.taxservice.repository;

import com.spickandspan.taxservice.entity.TaxRecordEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface TaxRecordRepository extends MongoRepository<TaxRecordEntity, String> {
    @Query("{productId:'?0'}")
    Optional<TaxRecordEntity> findItemByProductId(Long productId);
}
