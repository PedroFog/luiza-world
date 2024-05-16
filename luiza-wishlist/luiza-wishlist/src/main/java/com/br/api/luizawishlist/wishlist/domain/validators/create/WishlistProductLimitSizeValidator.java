package com.br.api.luizawishlist.wishlist.domain.validators.create;

import org.springframework.stereotype.Component;

import com.br.api.luizawishlist.wishlist.domain.exceptions.WishlistMaxCapacityException;

@Component
public class WishlistProductLimitSizeValidator implements IWishlistCreateValidation {

	private static final int MAX_CAPACITY = 20;

	@Override
	public void validate(CreateValidationParams params) {
		if (params.getSearchedWishlist().getProducts().size() >= MAX_CAPACITY) {
			throw new WishlistMaxCapacityException("Wishlist already reached the limit of products: " + MAX_CAPACITY);
		}
	}
}
