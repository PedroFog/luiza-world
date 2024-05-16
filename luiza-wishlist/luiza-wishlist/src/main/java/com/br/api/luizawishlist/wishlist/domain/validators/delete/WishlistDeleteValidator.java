package com.br.api.luizawishlist.wishlist.domain.validators.delete;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class WishlistDeleteValidator implements IWishlistDeleteValidation{

	private final List<IWishlistDeleteValidation> validations;

	public WishlistDeleteValidator(List<IWishlistDeleteValidation> validations) {
		this.validations = validations;
	}


	@Override
	public void validate(DeleteValidationParams params) {
		for (IWishlistDeleteValidation validation : validations) {
			validation.validate(params);
		}
	}

}
