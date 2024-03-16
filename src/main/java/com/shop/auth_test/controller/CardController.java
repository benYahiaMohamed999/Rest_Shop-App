package com.shop.auth_test.controller;

import com.shop.auth_test.entity.CardEntity;
import com.shop.auth_test.repository.CradRepository;
import com.shop.auth_test.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/card")
public class CardController {
    @Autowired
    private CardService cardService;

    @PostMapping
    public CardEntity create(@RequestBody CardEntity entity) {
        return cardService.save(entity);
    }

    @GetMapping("/{id}")
    public CardEntity getById(@PathVariable String id) {
        return cardService.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Entity not found with id: " + id));
    }

    @GetMapping
    public List<CardEntity> getAll() {
        return cardService.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        cardService.deleteById(id);
    }
    @PutMapping("/{id}")
    public CardEntity update(@PathVariable String id, @RequestBody CardEntity updatedEntity) {
        return cardService.update(id, updatedEntity);
    }


}
