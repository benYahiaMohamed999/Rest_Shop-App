package com.shop.auth_test.services;

import com.shop.auth_test.entity.CardEntity;
import com.shop.auth_test.entity.ProductEntity;
import com.shop.auth_test.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductEntity save(ProductEntity entity) {
        return productRepository.save(entity);
    }


    public ProductEntity update(String id, ProductEntity updatedEntity) {
        Optional<ProductEntity> optionalEntity = productRepository.findById(id);
        if (optionalEntity.isPresent()) {
            ProductEntity entity = optionalEntity.get();
            // Update fields as needed
            entity.setImageUrl(updatedEntity.getImageUrl());
            entity.setCategory(updatedEntity.getCategory());
            entity.setColor(updatedEntity.getColor());
            entity.setPrice(updatedEntity.getPrice());
            entity.setDiscount(updatedEntity.getDiscount());
            entity.setProductName(updatedEntity.getProductName());
            entity.setAddedInOrder(updatedEntity.getAddedInOrder());
            entity.setQuantity(updatedEntity.getQuantity());
            // Save the updated entity
            return productRepository.save(entity);
        } else {
            throw new UsernameNotFoundException("Entity not found with id: " + id);
        }
    }
    public Optional<ProductEntity> findById(String id) {
        return productRepository.findById(id);
    }

    public List<ProductEntity> findAll() {
        return productRepository.findAll();
    }

    public List<ProductEntity> findAllInOrder(ProductEntity entity) {
        return productRepository.findAllByAddedInOrder(entity.getAddedInOrder().booleanValue());
    }

    public void deleteById(String id) {
        productRepository.deleteById(id);
    }
}
