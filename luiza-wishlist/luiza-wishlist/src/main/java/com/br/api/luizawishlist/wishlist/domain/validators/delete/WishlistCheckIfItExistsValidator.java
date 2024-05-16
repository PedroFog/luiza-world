package com.br.api.luizawishlist.wishlist.domain.validators.delete;

import org.springframework.stereotype.Component;

import com.br.api.luizawishlist.wishlist.domain.exceptions.WishlistNotFoundException;

@Component
public class WishlistCheckIfItExistsValidator implements IWishlistDeleteValidation {

	@Override
	public void validate(DeleteValidationParams params) {
		if (params.getSearchedWishlist() == null) {
			throw new WishlistNotFoundException("There is no withlist to this client to delete");
		}
	}
}
