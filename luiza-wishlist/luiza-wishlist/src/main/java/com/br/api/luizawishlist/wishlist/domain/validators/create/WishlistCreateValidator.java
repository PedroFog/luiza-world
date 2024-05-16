package com.br.api.luizawishlist.wishlist.domain.validators.create;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class WishlistCreateValidator implements IWishlistCreateValidation {

	private final List<IWishlistCreateValidation> validations;
	
	public WishlistCreateValidator(List<IWishlistCreateValidation> validations) {
		this.validations = validations;
	}

	@Override
	public void validate(CreateValidationParams params) {
		for (IWishlistCreateValidation validation : validations) {
			validation.validate(params);
		}
	}

}
