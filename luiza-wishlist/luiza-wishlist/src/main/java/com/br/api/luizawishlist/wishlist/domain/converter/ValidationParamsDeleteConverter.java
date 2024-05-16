package com.br.api.luizawishlist.wishlist.domain.converter;

import org.springframework.stereotype.Component;

import com.br.api.luizawishlist.wishlist.domain.entities.Wishlist;
import com.br.api.luizawishlist.wishlist.domain.validators.delete.DeleteValidationParams;

@Component
public class ValidationParamsDeleteConverter {

	 public DeleteValidationParams toFindAllValidationParams(Wishlist searchedWishlist, String productId) {
		 DeleteValidationParams params = new DeleteValidationParams();
	        params.setSearchedWishlist(searchedWishlist);
	        params.setProductId(productId);
	        return params;
	    }
}
