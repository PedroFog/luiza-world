package com.br.api.luizawishlist.wishlist.domain.usecases;

import org.springframework.stereotype.Service;

import com.br.api.luizawishlist.wishlist.domain.converter.ValidationParamsCreateConverter;
import com.br.api.luizawishlist.wishlist.domain.entities.Wishlist;
import com.br.api.luizawishlist.wishlist.domain.repositories.IWishlistRepository;
import com.br.api.luizawishlist.wishlist.domain.validators.create.WishlistCreateValidator;

@Service
public class CreateWishlistProduct {

	private final IWishlistRepository wishlistRepository;
	private final WishlistCreateValidator wishlistValidator;
	private final ValidationParamsCreateConverter validationParamsCreateConverter;

	public CreateWishlistProduct(IWishlistRepository wishlistRepository, WishlistCreateValidator wishlistValidator,
			ValidationParamsCreateConverter validationParamsCreateConverter) {
		this.wishlistRepository = wishlistRepository;
		this.wishlistValidator = wishlistValidator;
		this.validationParamsCreateConverter = validationParamsCreateConverter;
	}

	public void call(Wishlist wishlist) throws Exception {
		var existsWishlist = wishlistRepository.existsByClientId(wishlist.getClientId());
		if (!existsWishlist) {
			wishlistRepository.saveWishlistProduct(wishlist);
		} else {
			var createdWishlist = wishlistRepository.findByClientId(wishlist.getClientId());
			this.wishlistValidator.validate(validationParamsCreateConverter.toCreateValidationParams(wishlist,
					createdWishlist, wishlist.getProducts().stream().findFirst().get().getProductId()));
			createdWishlist.getProducts().add(wishlist.getProducts().stream().findFirst().get());
			wishlistRepository.saveWishlistProduct(createdWishlist);
		}
	}
}
