package com.br.api.luizawishlist.wishlist.data.datasources;

import java.util.List;

import com.br.api.luizawishlist.wishlist.data.models.ProductModel;
import com.br.api.luizawishlist.wishlist.data.models.WishlistModel;
import com.br.api.luizawishlist.wishlist.data.models.WishlistPageModel;


public interface IWishlistDatasourceLocal {
	
	public void saveWishlistProduct(WishlistModel wishlistModel);

	public List<ProductModel> getAllProductsWishlistClient(WishlistPageModel wishlistPageModel);

	public WishlistModel findByClientId(String clientId);
	
	public boolean existsByClientId(String clientId);

	public void deleteById(String id);

	public boolean productExistInWishlistByClientId(String clientId, String productId);


}
