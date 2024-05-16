package com.br.api.luizawishlist.wishlist.data.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.br.api.luizawishlist.wishlist.data.models.ProductModel;
import com.br.api.luizawishlist.wishlist.data.models.ProductPageResponseModel;
import com.br.api.luizawishlist.wishlist.domain.entities.Product;

@Component
public class ProductConverter {
	
	public Product toProduct(ProductModel productModel) {
		if (productModel == null) {
			return null;
		}
		var product = new Product();
		BeanUtils.copyProperties(productModel, product);
		return product;
	}

	public ProductModel toProductModel(Product product) {
		if (product == null) {
			return null;
		}
		var productModel = new ProductModel();
		BeanUtils.copyProperties(product, productModel);
		return productModel;
	}

	public ProductPageResponseModel toProductPageResponse(List<Product> allValues) {
		if(allValues == null || allValues.isEmpty()) {
			return null;
		}
		
		return new ProductPageResponseModel(allValues.stream().map(Product::getProductId).collect(Collectors.toList()));
	}

}
