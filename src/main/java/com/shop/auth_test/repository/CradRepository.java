package com.shop.auth_test.repository;

import com.shop.auth_test.entity.CardEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableMongoRepositories
public interface CradRepository extends MongoRepository<CardEntity,String> {


}
