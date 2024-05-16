package com.br.api.luizawishlist.wishlist.domain.repositories;

import java.util.List;

import com.br.api.luizawishlist.wishlist.domain.entities.Product;
import com.br.api.luizawishlist.wishlist.domain.entities.Wishlist;
import com.br.api.luizawishlist.wishlist.domain.entities.WishlistPage;

public interface IWishlistRepository {

	public void saveWishlistProduct(Wishlist wishlist);

	public List<Product> getAllProductsWishlistClient(WishlistPage wishlistPage);

	public Wishlist findByClientId(String clientId);
	
	public boolean existsByClientId(String clientId);
	
	public boolean productExistInWishlistByClientId(String clientId, String productId);

	public void deleteWishlistById(String id);

}
