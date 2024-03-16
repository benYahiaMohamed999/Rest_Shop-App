package com.shop.auth_test.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "card")
@Getter
@Setter
@Data
public class CardEntity {
    @MongoId
    private String id;
    private String imageUrl;
    private String description;
}
