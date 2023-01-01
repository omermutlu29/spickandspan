package com.spickandspan.user.product.application.controller;

import com.spickandspan.user.product.application.service.ProductService;
import com.spickandspan.user.product.domain.dto.req.CreateProductRequest;
import com.spickandspan.user.product.domain.dto.req.UpdateProductRequest;
import com.spickandspan.user.product.domain.dto.res.ProductResponseDTO;
import com.spickandspan.user.user.application.service.UserService;
import org.hibernate.sql.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/products/api/v1")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts(){
        return new ResponseEntity<>(this.productService.getAll(),HttpStatus.OK);
    }

    @GetMapping("/findById/{productId}")
    public ResponseEntity<ProductResponseDTO> findProductById(@PathVariable("productId") Long productId){
        return new ResponseEntity<>(this.productService.getById(productId),HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ProductResponseDTO> create(Principal principal, @RequestBody CreateProductRequest createProductRequest) {
        return new ResponseEntity<>(this.productService.create(createProductRequest, principal.getName()), HttpStatus.CREATED);
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity<ProductResponseDTO> update(@PathVariable("productId") Long productId, @RequestBody UpdateProductRequest updateProductRequest) {
        return new ResponseEntity<>(this.productService.update(productId,updateProductRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteById/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductById(@PathVariable("productId") Long productId){
        this.productService.deleteProduct(productId);
    }
}
