package com.br.api.luizawishlist.cucumber.tests.util;

import java.util.ArrayList;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import com.br.api.luizawishlist.wishlist.domain.entities.Product;
import com.br.api.luizawishlist.wishlist.domain.entities.Wishlist;

@Component
public class MockWishlist {
	
	public Wishlist createFullWishlist(String clientId, int numProducts) {
		Wishlist wishlist = new Wishlist(clientId);
		ArrayList<Product> products = new ArrayList<>();
		for (int i = 0; i < numProducts; i++) {
			products.add(new Product(new ObjectId()));
		}
		wishlist.setProducts(products);
		return wishlist;
	}

}
