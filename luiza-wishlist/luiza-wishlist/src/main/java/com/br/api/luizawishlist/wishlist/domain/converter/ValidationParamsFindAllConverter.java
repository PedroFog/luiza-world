package com.br.api.luizawishlist.wishlist.domain.converter;

import java.util.List;

import org.springframework.stereotype.Component;

import com.br.api.luizawishlist.wishlist.domain.entities.Product;
import com.br.api.luizawishlist.wishlist.domain.validators.find_all.FindAllValidationParams;

@Component
public class ValidationParamsFindAllConverter {

    public FindAllValidationParams toFindAllValidationParams(boolean existsWishlist, List<Product> products) {
        FindAllValidationParams params = new FindAllValidationParams();
        params.setExistsWishlist(existsWishlist);
        params.setListProducts(products);
        return params;
    }
}
