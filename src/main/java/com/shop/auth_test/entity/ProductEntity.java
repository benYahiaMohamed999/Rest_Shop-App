package com.shop.auth_test.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
@Document(collection = "product")
@Data
@Setter
@Getter
public class ProductEntity {

    @MongoId
    private String productId;
    private String productName;
    private Double price;
    private String color;
    private Boolean addedInOrder;
    private String imageUrl;
    private Integer quantity;
    private Boolean discount;
    private CategorieEntity category;
}
