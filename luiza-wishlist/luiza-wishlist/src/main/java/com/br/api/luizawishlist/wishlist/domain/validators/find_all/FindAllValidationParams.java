package com.br.api.luizawishlist.wishlist.domain.validators.find_all;

import java.util.List;

import com.br.api.luizawishlist.wishlist.domain.entities.Product;

public class FindAllValidationParams {
	
	private boolean existsWishlist;
	private List<Product> listProducts;
	
	public boolean isExistsWishlist() {
		return existsWishlist;
	}
	public void setExistsWishlist(boolean existsWishlist) {
		this.existsWishlist = existsWishlist;
	}
	public List<Product> getListProducts() {
		return listProducts;
	}
	public void setListProducts(List<Product> listProducts) {
		this.listProducts = listProducts;
	}
	
	

}
