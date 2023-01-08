package com.spickandspan.taxservice.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductTestEvent {
    private Long id;
    private BigDecimal price;
    private EventType eventType;
    private String testString;

}
