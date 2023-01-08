package com.spickandspan.productservice.product.application.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductEvent implements Event{
    private Long id;
    private BigDecimal price;
    private EventType eventType;

    @Override
    public String getType() {
        return "ProductEvent";
    }
}


