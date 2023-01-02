package com.spickandspan.taxservice.controller;

import com.spickandspan.taxservice.service.TaxService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("tax/api/v1")
public class TaxController {
    private final TaxService taxService;

    public TaxController(TaxService taxService) {
        this.taxService = taxService;
    }

    @GetMapping("/calculate/{productId}")
    public ResponseEntity<BigDecimal> getProductTax(@PathVariable Long productId){
        return ResponseEntity.ok(this.taxService.getProductTax(productId));
    }
}
