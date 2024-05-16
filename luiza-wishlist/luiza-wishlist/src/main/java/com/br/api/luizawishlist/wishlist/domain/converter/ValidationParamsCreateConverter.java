package com.br.api.luizawishlist.wishlist.domain.converter;

import org.springframework.stereotype.Component;

import com.br.api.luizawishlist.wishlist.domain.entities.Wishlist;
import com.br.api.luizawishlist.wishlist.domain.validators.create.CreateValidationParams;

@Component
public class ValidationParamsCreateConverter {

	public CreateValidationParams toCreateValidationParams(Wishlist convertedWishlist, Wishlist searchedWishlist,
			String productId) {
		CreateValidationParams params = new CreateValidationParams();
		params.setConvertedWishlist(convertedWishlist);
		params.setSearchedWishlist(searchedWishlist);
		params.setProductId(productId);
		return params;
	}
}
