package com.br.api.luizawishlist.wishlist.domain.validators.find_all;

import org.springframework.stereotype.Component;

import com.br.api.luizawishlist.wishlist.domain.exceptions.WishlistNotFoundException;

@Component
public class WishlistFindAllCheckIfExistsValidator implements IWishlistFindAllValidation {

	@Override
	public void validate(FindAllValidationParams params) {
		if(!params.isExistsWishlist()) {
			throw new WishlistNotFoundException("There is no withlist to this client to find all");
		}
	}

}
