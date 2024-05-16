package com.br.api.luizawishlist.wishlist.domain.validators.find_all;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class WishlistFindAllValidator implements IWishlistFindAllValidation {

	private final List<IWishlistFindAllValidation> validations;

	public WishlistFindAllValidator(List<IWishlistFindAllValidation> validations) {
		this.validations = validations;
	}

	@Override
	public void validate(FindAllValidationParams params) {
		for (IWishlistFindAllValidation validation : validations) {
			validation.validate(params);
		}
	}

}
