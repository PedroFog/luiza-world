package com.br.api.luizawishlist.wishlist.data.models;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "wishlists")
public class WishlistModel {

	@Id
	private ObjectId id;

	@Field("clientId")
	private ObjectId clientId;

	private List<ProductModel> products;

	public String getId() {
		return id.toHexString();
	}

	public void setId(String id) {
		this.id = null;
		if (id != null) {
			this.id = new ObjectId(id);
		} 
	}

	public String getClientId() {
		return clientId.toHexString();
	}

	public void setClientId(String clientId) {
		this.clientId = new ObjectId(clientId);
	}

	public List<ProductModel> getProducts() {
		return products;
	}

	public void setProducts(List<ProductModel> products) {
		this.products = products;
	}

}
