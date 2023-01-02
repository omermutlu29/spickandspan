package com.spickandspan.productservice.product.application.controller;

import com.spickandspan.productservice.product.application.service.ProductService;
import com.spickandspan.productservice.product.domain.dto.req.CreateProductRequest;
import com.spickandspan.productservice.product.domain.dto.req.UpdateProductRequest;
import com.spickandspan.productservice.product.domain.dto.res.ProductResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;
import java.security.Principal;

@RestController
@RequestMapping("/products/api/v1")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<Page<ProductResponseDTO>> getAllProducts(@QueryParam("page") Integer page) {
        return new ResponseEntity<>(this.productService.getAll(page), HttpStatus.OK);
    }

    @GetMapping("/findById/{productId}")
    public ResponseEntity<ProductResponseDTO> findProductById(@PathVariable("productId") Long productId) {
        return new ResponseEntity<>(this.productService.getById(productId), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ProductResponseDTO> create(Principal principal, @RequestBody CreateProductRequest createProductRequest) {
        System.out.println(principal.getName());
        return new ResponseEntity<>(this.productService.create(createProductRequest, principal.getName()), HttpStatus.CREATED);
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity<ProductResponseDTO> update(Principal principal,@PathVariable("productId") Long productId, @RequestBody UpdateProductRequest updateProductRequest) {

        return new ResponseEntity<>(this.productService.update(principal.getName(),productId, updateProductRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteById/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductById(Principal principal,@PathVariable("productId") Long productId) {
        this.productService.deleteProduct(principal.getName(),productId);
    }
}
