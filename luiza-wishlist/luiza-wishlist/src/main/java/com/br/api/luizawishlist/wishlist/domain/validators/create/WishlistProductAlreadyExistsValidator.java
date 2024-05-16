package com.br.api.luizawishlist.wishlist.domain.validators.create;

import org.springframework.stereotype.Component;

import com.br.api.luizawishlist.wishlist.domain.exceptions.WishlistProductAlreadyExistsInException;

@Component
public class WishlistProductAlreadyExistsValidator implements IWishlistCreateValidation {



	@Override
	public void validate(CreateValidationParams params) {
		if (params.getSearchedWishlist().getProducts().stream()
				.anyMatch(wp -> wp.getProductId().equals(params.getProductId()))) {
			throw new WishlistProductAlreadyExistsInException("Product already exists in this wishlist");
		}
	}

}
