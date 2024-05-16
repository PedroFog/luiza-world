package com.br.api.luizawishlist.wishlist.data.converter;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.br.api.luizawishlist.wishlist.data.models.ProductModel;
import com.br.api.luizawishlist.wishlist.data.models.WishlistModel;
import com.br.api.luizawishlist.wishlist.data.models.WishlistPageModel;
import com.br.api.luizawishlist.wishlist.data.models.ProductPageRequestModel;
import com.br.api.luizawishlist.wishlist.data.models.WishlistRequestModel;
import com.br.api.luizawishlist.wishlist.domain.entities.Wishlist;
import com.br.api.luizawishlist.wishlist.domain.entities.WishlistPage;

@Component
public class WishlistConverter {
	
	private final ProductConverter productConverter;
	
	public WishlistConverter(ProductConverter productConverter) {
		this.productConverter = productConverter;
	}
	
	public Wishlist toWishlist(WishlistModel wishlistModel) {
		if (wishlistModel == null) {
			return null;
		}
		var wishlist = new Wishlist();
		wishlist.setId(wishlistModel.getId());
		wishlist.setClientId(wishlistModel.getClientId());
		wishlist.setProducts(wishlistModel.getProducts().stream().map(productConverter::toProduct).collect(Collectors.toList()));
		return wishlist;
	}

	public WishlistModel toWishlistModel(Wishlist wishlist) {
		if (wishlist == null) {
			return null;
		}
		var wishlistModel = new WishlistModel();

		wishlistModel.setId(wishlist.getId() != null ? wishlist.getId() : null);
		wishlistModel.setClientId(wishlist.getClientId());
		wishlistModel
				.setProducts(wishlist.getProducts().stream().map(productConverter::toProductModel).collect(Collectors.toList()));
		return wishlistModel;
	}


	public Wishlist toWishlist(WishlistRequestModel wishlistRequestModel) {
		if (wishlistRequestModel == null) {
			return null;
		}
		var wishlist = new Wishlist();
		BeanUtils.copyProperties(wishlistRequestModel, wishlist);
		var product = new ProductModel(new ObjectId(wishlistRequestModel.getProductId()));
		wishlist.setProducts(Arrays.asList(productConverter.toProduct(product)));
		return wishlist;

	}

	public WishlistPage toWishlistPage(ProductPageRequestModel wishlistPageRequestModel) {
		if (wishlistPageRequestModel == null) {
			return null;
		}
		
		var wishlistPage = new WishlistPage();
		BeanUtils.copyProperties(wishlistPageRequestModel, wishlistPage);
		return wishlistPage;
	}

	public WishlistPageModel toWishlistPageModel(WishlistPage wishlistPage) {
		if (wishlistPage == null) {
			return null;
		}
		
		var wishlistPageModel = new WishlistPageModel();
		BeanUtils.copyProperties(wishlistPage, wishlistPageModel);
		return wishlistPageModel;
	}
	
}
