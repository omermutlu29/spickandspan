package com.spickandspan.productservice.product.application.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductEvent {
    private Long id;
    private BigDecimal price;
    private EventType eventType;



}
