package com.spickandspan.productservice.product.application.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductTestEvent implements Event{
    private Long id;
    private BigDecimal price;
    private EventType eventType;
    private String testString;

    @Override
    public String getType() {
        return "ProductTestEvent";
    }
}
