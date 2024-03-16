package com.shop.auth_test.controller;

import com.shop.auth_test.entity.CardEntity;
import com.shop.auth_test.entity.ProductEntity;
import com.shop.auth_test.services.ProductService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")

public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ProductEntity create(@RequestBody ProductEntity entity) {
        return productService.save(entity);
    }

    @GetMapping("/{id}")
    public ProductEntity getById(@PathVariable String id) {
        return productService.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Entity not found with id: " + id));
    }

    @GetMapping
    public List<ProductEntity> getAll() {
        return productService.findAll();
    }

    @GetMapping("/inOrder")
    public List<ProductEntity> findAllInOrder(@RequestBody ProductEntity entity) {
        return productService.findAllInOrder(entity);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        productService.deleteById(id);
    }
    @PutMapping("/{id}")
    public ProductEntity update(@PathVariable String id, @RequestBody ProductEntity updatedEntity) {
        return productService.update(id, updatedEntity);
    }



}
