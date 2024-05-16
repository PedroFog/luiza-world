package com.br.api.luizawishlist.wishlist.data.datasources;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.br.api.luizawishlist.wishlist.data.models.WishlistModel;

@Repository
public interface IMongoWishlistRepository extends MongoRepository<WishlistModel, ObjectId> {

	Optional<WishlistModel> findByClientId(ObjectId clientId);

	boolean existsByClientId(ObjectId clientId);

	@Query("{'clientId': ?0, 'products.productId': ?1}")
	Optional<WishlistModel> existsByClientIdAndProductId(ObjectId clientId, ObjectId productId);


}
