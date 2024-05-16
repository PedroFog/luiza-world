package com.br.api.luizawishlist.wishlist.domain.usecases;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.br.api.luizawishlist.wishlist.domain.exceptions.WishlistNotFoundException;
import com.br.api.luizawishlist.wishlist.domain.repositories.IWishlistRepository;
import com.br.api.luizawishlist.wishlist.domain.validators.WishlistObjectIdValidator;

@Service
public class GetProductWishlistClient {

	private final IWishlistRepository wishlistRepository;
	private final WishlistObjectIdValidator wishlistObjectIdValidator;

	public GetProductWishlistClient(IWishlistRepository wishlistRepository,
			WishlistObjectIdValidator wishlistObjectIdValidator) {
		this.wishlistRepository = wishlistRepository;
		this.wishlistObjectIdValidator = wishlistObjectIdValidator;
	}

	public boolean call(String clientId, String productId) {

		this.wishlistObjectIdValidator.validate(Arrays.asList(clientId, productId));

		var existsWishlist = wishlistRepository.existsByClientId(clientId);

		if (!existsWishlist) {
			throw new WishlistNotFoundException("This wishlist was not found");
		}

		 return wishlistRepository.productExistInWishlistByClientId(clientId, productId);
	}

}
