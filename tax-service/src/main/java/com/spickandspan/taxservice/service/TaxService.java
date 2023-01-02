package com.spickandspan.taxservice.service;

import com.spickandspan.taxservice.entity.TaxRecordEntity;
import com.spickandspan.taxservice.event.EventType;
import com.spickandspan.taxservice.event.ProductEvent;
import com.spickandspan.taxservice.exception.EntityNotFoundException;
import com.spickandspan.taxservice.repository.TaxRecordRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TaxService {
    private final BigDecimal TAX_RATIO = BigDecimal.valueOf(0.2);
    private final TaxRecordRepository taxRecordRepository;

    public TaxService(TaxRecordRepository taxRecordRepository) {
        this.taxRecordRepository = taxRecordRepository;
    }

    public BigDecimal getProductTax(Long productId) {
        TaxRecordEntity taxRecordEntity = findByProductId(productId);
        return taxRecordEntity.getTaxOfProduct();
    }

    public void handleEventService(ProductEvent productEvent) {
        if (productEvent.getEventType() == EventType.INSERT) {
            TaxRecordEntity taxRecordEntity = mapProductEventToTaxEntity(productEvent);
            this.taxRecordRepository.save(taxRecordEntity);
        }
        if (productEvent.getEventType() == EventType.UPDATE) {
            TaxRecordEntity taxRecordEntityMapped = mapProductEventToTaxEntity(productEvent);
            this.taxRecordRepository.save(taxRecordEntityMapped);
        }
        if (productEvent.getEventType() == EventType.DELETE) {
            System.out.println(productEvent.getEventType() + " " + productEvent.getId());
            List<TaxRecordEntity> taxRecordEntities = this.taxRecordRepository.findByProductId(productEvent.getId());
            System.out.println(taxRecordEntities.get(0).getTaxOfProduct());
            this.taxRecordRepository.deleteAll(taxRecordEntities);
        }
    }

    private TaxRecordEntity findByProductId(Long productId) {
        return taxRecordRepository.findItemByProductId(productId).orElseThrow(EntityNotFoundException::new);
    }

    private TaxRecordEntity mapProductEventToTaxEntity(ProductEvent productEvent) {
        TaxRecordEntity taxRecordEntity = new TaxRecordEntity();
        taxRecordEntity.setTaxOfProduct(TAX_RATIO.multiply(productEvent.getPrice()));
        taxRecordEntity.setProductId(productEvent.getId());
        taxRecordEntity.setEventType(productEvent.getEventType());
        return taxRecordEntity;
    }
}
