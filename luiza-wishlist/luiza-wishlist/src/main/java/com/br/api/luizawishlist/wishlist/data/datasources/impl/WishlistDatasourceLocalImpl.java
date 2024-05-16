package com.br.api.luizawishlist.wishlist.data.datasources.impl;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import com.br.api.luizawishlist.wishlist.data.datasources.IMongoWishlistRepository;
import com.br.api.luizawishlist.wishlist.data.datasources.IWishlistDatasourceLocal;
import com.br.api.luizawishlist.wishlist.data.models.ProductModel;
import com.br.api.luizawishlist.wishlist.data.models.WishlistModel;
import com.br.api.luizawishlist.wishlist.data.models.WishlistPageModel;

@Component
public class WishlistDatasourceLocalImpl implements IWishlistDatasourceLocal {

	private final IMongoWishlistRepository mongoWishlistRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	public WishlistDatasourceLocalImpl(IMongoWishlistRepository mongoWishlistRepository) {
		this.mongoWishlistRepository = mongoWishlistRepository;
	}

	@Override
	public void saveWishlistProduct(WishlistModel wishlist) {
		mongoWishlistRepository.save(wishlist);
	}

	@Override
	public List<ProductModel> getAllProductsWishlistClient(WishlistPageModel wishlistPageModel) {
		MatchOperation matchOperation = Aggregation.match(Criteria.where("clientId").is(new ObjectId(wishlistPageModel.getClientId())));
		UnwindOperation unwindOperation = Aggregation.unwind("products");
		ProjectionOperation projectionOperation = Aggregation.project("products.productId", "products.dateCreate")
				.andExclude("_id");
		Aggregation aggregation = Aggregation.newAggregation(matchOperation, unwindOperation, projectionOperation,
				Aggregation.skip((long) wishlistPageModel.getPage()), Aggregation.limit(5));

		AggregationResults<ProductModel> results = mongoTemplate.aggregate(aggregation, "wishlists",
				ProductModel.class);
		return results.getMappedResults();
	}

	@Override
	public WishlistModel findByClientId(String clientId) {
		return mongoWishlistRepository.findByClientId(new ObjectId(clientId)).orElse(null);
	}

	@Override
	public boolean existsByClientId(String clientId) {
		return mongoWishlistRepository.existsByClientId(new ObjectId(clientId));
	}

	@Override
	public void deleteById(String id) {
		mongoWishlistRepository.deleteById(new ObjectId(id));
	}

	@Override
	public boolean productExistInWishlistByClientId(String clientId, String productId) {
		return mongoWishlistRepository.existsByClientIdAndProductId(new ObjectId(clientId), new ObjectId(productId))
				.isPresent();
	}

}
