package com.br.api.luizawishlist.wishlist.domain.validators.delete;

import com.br.api.luizawishlist.wishlist.domain.entities.Wishlist;

public class DeleteValidationParams {

	private Wishlist searchedWishlist;
	private String productId;
	
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
