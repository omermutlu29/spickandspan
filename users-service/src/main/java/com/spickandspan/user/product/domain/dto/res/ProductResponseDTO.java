package com.spickandspan.user.product.domain.dto.res;

import com.spickandspan.user.user.domain.dto.res.UserResponse;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@RequiredArgsConstructor
public class ProductResponseDTO {
    private UserResponse userResponse;
    private String name;
    private String description;
    private BigDecimal price;
}
