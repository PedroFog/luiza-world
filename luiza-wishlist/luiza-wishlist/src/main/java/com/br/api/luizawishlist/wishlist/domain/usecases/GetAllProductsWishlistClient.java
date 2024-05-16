package com.br.api.luizawishlist.wishlist.domain.usecases;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.br.api.luizawishlist.wishlist.domain.converter.ValidationParamsFindAllConverter;
import com.br.api.luizawishlist.wishlist.domain.entities.Product;
import com.br.api.luizawishlist.wishlist.domain.entities.WishlistPage;
import com.br.api.luizawishlist.wishlist.domain.repositories.IWishlistRepository;
import com.br.api.luizawishlist.wishlist.domain.validators.WishlistObjectIdValidator;
import com.br.api.luizawishlist.wishlist.domain.validators.find_all.WishlistFindAllValidator;

@Service
public class GetAllProductsWishlistClient {

	private final IWishlistRepository wishlistRepository;
	private final WishlistObjectIdValidator wishlistObjectIdValidator;
	private final WishlistFindAllValidator wishlistFindAllValidator;
	private final ValidationParamsFindAllConverter validationParamsFindAllConverter;

	public GetAllProductsWishlistClient(IWishlistRepository wishlistRepository,
			WishlistObjectIdValidator wishlistObjectIdValidator, WishlistFindAllValidator wishlistFindAllValidator,
			ValidationParamsFindAllConverter validationParamsFindAllConverter) {
		this.wishlistRepository = wishlistRepository;
		this.wishlistObjectIdValidator = wishlistObjectIdValidator;
		this.wishlistFindAllValidator = wishlistFindAllValidator;
		this.validationParamsFindAllConverter = validationParamsFindAllConverter;
	}

	public List<Product> call(WishlistPage wishlistPage) {
		this.wishlistObjectIdValidator.validate(Arrays.asList(wishlistPage.getClientId()));

		wishlistPage.setPage(wishlistPage.getPage() * 5);

		var allProductsIds = wishlistRepository.getAllProductsWishlistClient(wishlistPage);
		var existsWishlist = wishlistRepository.existsByClientId(wishlistPage.getClientId());

		wishlistFindAllValidator
				.validate(validationParamsFindAllConverter.toFindAllValidationParams(existsWishlist, allProductsIds));

		return allProductsIds;
	}

}