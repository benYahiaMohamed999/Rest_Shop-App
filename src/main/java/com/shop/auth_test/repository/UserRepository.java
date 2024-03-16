package com.shop.auth_test.repository;

import com.shop.auth_test.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableMongoRepositories
public interface UserRepository extends MongoRepository<UserEntity,String> {
    Optional<UserEntity>findByusername(String username);
}
