package com.br.api.luizawishlist.wishlist.domain.validators;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import com.br.api.luizawishlist.wishlist.domain.exceptions.ValidationObjectIdException;

@Component
public class WishlistObjectIdValidator implements IWishlistObjectIdValidator {

	@Override
	public void validate(List<String> listObjectsId) {

		listObjectsId.stream().forEach(ol -> {
			if (!ObjectId.isValid(ol)) {
				throw new ValidationObjectIdException("Invalid ObjectId format since must be exactly 24 hex characters");
			}
		});
	}
}
