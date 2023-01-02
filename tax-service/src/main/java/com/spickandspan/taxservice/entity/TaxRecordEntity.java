package com.spickandspan.taxservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("tax_records")
public class TaxRecordEntity {

    @Id
    private String id;

    Long productId;

    BigDecimal taxOfProduct;

}
