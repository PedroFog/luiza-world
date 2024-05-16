package com.br.api.luizawishlist.wishlist.data.models;

import java.util.List;

public class ProductPageResponseModel {

	private List<String> productsIds;

	public ProductPageResponseModel(List<String> listProductsIds) {
		this.productsIds = listProductsIds;
	}

	public List<String> getProductsIds() {
		return productsIds;
	}

	public void setProductsIds(List<String> productsIds) {
		this.productsIds = productsIds;
	}

	
	
}
