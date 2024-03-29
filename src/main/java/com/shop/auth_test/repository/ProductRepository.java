package com.shop.auth_test.repository;

import com.shop.auth_test.entity.ProductEntity;
import com.shop.auth_test.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableMongoRepositories
public interface ProductRepository extends MongoRepository<ProductEntity,String> {

    List<ProductEntity> findAllByAddedInOrder(boolean addedInOrder);
}
