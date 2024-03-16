package com.shop.auth_test.controller;

import com.shop.auth_test.entity.CategorieEntity;
import com.shop.auth_test.services.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoriesController {

    @Autowired
    CategorieService categorieService ;



    @PostMapping
    public CategorieEntity create(@RequestBody CategorieEntity entity) {

        try {
            boolean categorieExiste=categorieService.categoryExists(entity.getName());
            if(categorieExiste){

                return (CategorieEntity) ResponseEntity.status(HttpStatus.BAD_REQUEST);
            }else {
                return categorieService.save(entity);

            }
        }catch (Exception e){
            return (CategorieEntity) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
        }



    }

    @GetMapping("/{id}")
    public CategorieEntity getById(@PathVariable String id) {
        return categorieService.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Entity not found with id: " + id));
    }

    @GetMapping
    public List<CategorieEntity> getAll() {
        return categorieService.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        categorieService.deleteById(id);
    }
    @PutMapping("/{id}")
    public CategorieEntity update(@PathVariable String id, @RequestBody CategorieEntity updatedEntity) {
        return categorieService.update(id, updatedEntity);
    }









}
