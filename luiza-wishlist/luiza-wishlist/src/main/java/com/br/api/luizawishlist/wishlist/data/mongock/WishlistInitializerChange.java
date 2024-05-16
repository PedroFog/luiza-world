package com.br.api.luizawishlist.wishlist.data.mongock;

import java.util.Arrays;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import com.br.api.luizawishlist.wishlist.data.models.ProductModel;
import com.br.api.luizawishlist.wishlist.data.models.WishlistModel;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;

@ChangeUnit(id="client-initializer", order = "001", author = "mongock")
public class WishlistInitializerChange {
	private final MongoTemplate mongoTemplate;

    public WishlistInitializerChange(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Execution
    public void changeSet() {
        ProductModel product = new ProductModel(new ObjectId()); 
        WishlistModel wishlist = new WishlistModel();
        wishlist.setClientId("5e3fabcd6789123456789018");
        wishlist.setProducts(Arrays.asList(product));

        mongoTemplate.save(wishlist, "wishlists");
    }

    @RollbackExecution
    public void rollback() {
        mongoTemplate.remove(new Query(), "wishlists");
    }
}
