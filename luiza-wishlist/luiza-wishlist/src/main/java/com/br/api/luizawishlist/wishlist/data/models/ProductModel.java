package com.br.api.luizawishlist.wishlist.data.models;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Field;

public class ProductModel {

	@Field("productId")
	private ObjectId productId;

	public ProductModel(ObjectId productId) {
		this.productId = productId;
	}

	public ProductModel() {
	}

	public String getProductId() {
		return productId.toHexString();
	}

	public void setProductId(String productId) {
		this.productId = new ObjectId(productId);
	}


}
