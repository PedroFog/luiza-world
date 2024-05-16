package com.br.api.luizawishlist.wishlist.domain.usecases;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.br.api.luizawishlist.wishlist.domain.converter.ValidationParamsDeleteConverter;
import com.br.api.luizawishlist.wishlist.domain.entities.Product;
import com.br.api.luizawishlist.wishlist.domain.repositories.IWishlistRepository;
import com.br.api.luizawishlist.wishlist.domain.validators.WishlistObjectIdValidator;
import com.br.api.luizawishlist.wishlist.domain.validators.delete.WishlistDeleteValidator;

@Service
public class DeleteWishlistProduct {

	private final IWishlistRepository wishlistRepository;
	private final WishlistDeleteValidator wishlistDeleteValidator;
	private final WishlistObjectIdValidator wishlistObjectIdValidator;
	private final ValidationParamsDeleteConverter validationParamsDeleteConverter;

	public DeleteWishlistProduct(IWishlistRepository wishlistRepository,
			WishlistDeleteValidator wishlistDeleteValidator, WishlistObjectIdValidator wishlistObjectIdValidator, ValidationParamsDeleteConverter validationParamsDeleteConverter) {
		this.wishlistRepository = wishlistRepository;
		this.wishlistDeleteValidator = wishlistDeleteValidator;
		this.wishlistObjectIdValidator = wishlistObjectIdValidator;
		this.validationParamsDeleteConverter = validationParamsDeleteConverter;
	}

	public void call(String clientId, String productId) {

		this.wishlistObjectIdValidator.validate(Arrays.asList(clientId, productId));

		var wishlistSearch = wishlistRepository.findByClientId(clientId);

		wishlistDeleteValidator.validate(validationParamsDeleteConverter.toFindAllValidationParams(wishlistSearch, productId));

		List<Product> remainingProducts = wishlistSearch.getProducts().stream()
				.filter(product -> !product.getProductId().equals(productId)).collect(Collectors.toList());

		wishlistSearch.setProducts(remainingProducts);

		if (wishlistSearch.getProducts().size() < 1) {
			wishlistRepository.deleteWishlistById(wishlistSearch.getId());
		} else {
			wishlistRepository.saveWishlistProduct(wishlistSearch);
		}

	}

}
