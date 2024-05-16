package com.br.api.luizawishlist.wishlist.data.repositories;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.br.api.luizawishlist.wishlist.data.converter.ProductConverter;
import com.br.api.luizawishlist.wishlist.data.converter.WishlistConverter;
import com.br.api.luizawishlist.wishlist.data.datasources.IWishlistDatasourceLocal;
import com.br.api.luizawishlist.wishlist.data.models.ProductModel;
import com.br.api.luizawishlist.wishlist.domain.entities.Product;
import com.br.api.luizawishlist.wishlist.domain.entities.Wishlist;
import com.br.api.luizawishlist.wishlist.domain.entities.WishlistPage;
import com.br.api.luizawishlist.wishlist.domain.repositories.IWishlistRepository;

@Component
public class WishlistRepositoryImpl implements IWishlistRepository {

	private final IWishlistDatasourceLocal wishlistDatasourceLocal;
	private final WishlistConverter wishlistConverter;
	private final ProductConverter productConverter;

	public WishlistRepositoryImpl(IWishlistDatasourceLocal wishlistDatasourceLocal,
			WishlistConverter wishlistConverter, ProductConverter productConverter) {
		this.wishlistDatasourceLocal = wishlistDatasourceLocal;
		this.wishlistConverter = wishlistConverter;
		this.productConverter = productConverter;
	}

	@Override
	public void saveWishlistProduct(Wishlist wishlist) {
		var wishlistModel = wishlistConverter.toWishlistModel(wishlist);
		wishlistDatasourceLocal.saveWishlistProduct(wishlistModel);

	}

	@Override
	public List<Product> getAllProductsWishlistClient(WishlistPage wishlistPage) {
		List<ProductModel> models = wishlistDatasourceLocal.getAllProductsWishlistClient(wishlistConverter.toWishlistPageModel(wishlistPage));
        return models.stream().map(productConverter::toProduct).collect(Collectors.toList());
	}

	@Override
	public Wishlist findByClientId(String clientId) {
		 var wishlistModel = wishlistDatasourceLocal.findByClientId(clientId);
		 return wishlistConverter.toWishlist(wishlistModel);
	}

	@Override
	public boolean existsByClientId(String clientId) {
		return wishlistDatasourceLocal.existsByClientId(clientId);
	}

	@Override
	public void deleteWishlistById(String id) {
		wishlistDatasourceLocal.deleteById(id);		
	}

	@Override
	public boolean productExistInWishlistByClientId(String clientId, String productId) {
		return wishlistDatasourceLocal.productExistInWishlistByClientId(clientId, productId);
	}

}
