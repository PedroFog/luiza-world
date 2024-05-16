package com.br.api.luizawishlist.wishlist.domain.validators.find_all;

import org.springframework.stereotype.Component;

import com.br.api.luizawishlist.wishlist.domain.exceptions.NotMoreProductsInWishListException;

@Component
public class WishlistFindAllNoMorePagesLeft implements IWishlistFindAllValidation {

	@Override
	public void validate(FindAllValidationParams params) {
		if (params.getListProducts().isEmpty()) {
			throw new NotMoreProductsInWishListException("There is no more pages left with products");
		}
	}

}
