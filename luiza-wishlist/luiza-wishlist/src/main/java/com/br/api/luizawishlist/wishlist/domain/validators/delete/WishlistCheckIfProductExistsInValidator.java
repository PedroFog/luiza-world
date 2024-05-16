package com.br.api.luizawishlist.wishlist.domain.validators.delete;

import org.springframework.stereotype.Component;

import com.br.api.luizawishlist.wishlist.domain.exceptions.WishlistProductNotFoundInException;

@Component
public class WishlistCheckIfProductExistsInValidator implements IWishlistDeleteValidation {

	@Override
	public void validate(DeleteValidationParams params) {
		if (!params.getSearchedWishlist().getProducts().stream()
				.anyMatch(p -> p.getProductId().equals(params.getProductId()))) {
			throw new WishlistProductNotFoundInException("This product do not exist in this wishlist to remove it");
		}
	}

}
