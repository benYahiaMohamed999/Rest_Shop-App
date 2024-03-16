package com.shop.auth_test.services;

import com.shop.auth_test.entity.CardEntity;
import com.shop.auth_test.repository.CradRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardService {
    @Autowired
    private CradRepository cradRepository;


    public CardEntity save(CardEntity entity) {
        return cradRepository.save(entity);
    }


    public CardEntity update(String id, CardEntity updatedEntity) {
        Optional<CardEntity> optionalEntity = cradRepository.findById(id);
        if (optionalEntity.isPresent()) {
            CardEntity entity = optionalEntity.get();
            // Update fields as needed
            entity.setImageUrl(updatedEntity.getImageUrl());
            entity.setDescription(updatedEntity.getDescription());
            // Save the updated entity
            return cradRepository.save(entity);
        } else {
            throw new UsernameNotFoundException("Entity not found with id: " + id);
        }
    }
    public Optional<CardEntity> findById(String id) {
        return cradRepository.findById(id);
    }

    public List<CardEntity> findAll() {
        return cradRepository.findAll();
    }

    public void deleteById(String id) {
        cradRepository.deleteById(id);
    }

}
