package com.shop.auth_test.services;

import com.shop.auth_test.entity.CategorieEntity;
import com.shop.auth_test.entity.ProductEntity;
import com.shop.auth_test.repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategorieService {
    @Autowired
    private CategorieRepository  categorieRepository;

    public CategorieEntity save(CategorieEntity entity) {
        return categorieRepository.save(entity);
    }


    public CategorieEntity update(String id, CategorieEntity updatedEntity) {
        Optional<CategorieEntity> optionalEntity = categorieRepository.findById(id);
        if (optionalEntity.isPresent()) {
            CategorieEntity entity = optionalEntity.get();
            // Update fields as needed
           entity.setName(updatedEntity.getName());
            // Save the updated entity
            return categorieRepository.save(entity);
        } else {
            throw new UsernameNotFoundException("Entity not found with id: " + id);
        }
    }
    public Optional<CategorieEntity> findById(String id) {
        return categorieRepository.findById(id);
    }

    public List<CategorieEntity> findAll() {
        return categorieRepository.findAll();
    }

    public boolean categoryExists(String categoryName) {
        return categorieRepository.existsByName(categoryName);
    }



    public void deleteById(String id) {
        categorieRepository.deleteById(id);
    }
}
