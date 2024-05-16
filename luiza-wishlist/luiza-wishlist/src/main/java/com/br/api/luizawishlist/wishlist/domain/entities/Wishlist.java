package com.br.api.luizawishlist.wishlist.domain.entities;

import java.util.List;

import org.bson.types.ObjectId;

public class Wishlist {

	private ObjectId id;
	private ObjectId clientId;

	private List<Product> products;

	public Wishlist() {

	}

	public Wishlist(String clientId) {
		this.clientId = new ObjectId(clientId);
	}

	public String getId() {
		if (id != null) {
			return id.toHexString();
		}
		return null;
	}

	public void setId(String id) {
		this.id = new ObjectId(id);
	}

	public String getClientId() {
		return clientId.toHexString();
	}

	public void setClientId(String clientId) {
		this.clientId = new ObjectId(clientId);
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
