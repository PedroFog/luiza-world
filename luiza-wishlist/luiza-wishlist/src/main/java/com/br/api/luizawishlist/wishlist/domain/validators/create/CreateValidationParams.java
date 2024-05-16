package com.br.api.luizawishlist.wishlist.domain.validators.create;

import com.br.api.luizawishlist.wishlist.domain.entities.Wishlist;

public class CreateValidationParams {
	
	private Wishlist convertedWishlist;
	private Wishlist searchedWishlist;
	private String productId;
	
	public Wishlist getConvertedWishlist() {
		return convertedWishlist;
	}
	public void setConvertedWishlist(Wishlist convertedWishlist) {
		this.convertedWishlist = convertedWishlist;
	}
	public Wishlist getSearchedWishlist() {
		return searchedWishlist;
	}
	public void setSearchedWishlist(Wishlist searchedWishlist) {
		this.searchedWishlist = searchedWishlist;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	
	
	

}
