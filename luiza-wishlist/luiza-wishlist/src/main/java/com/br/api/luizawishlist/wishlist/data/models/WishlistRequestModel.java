package com.br.api.luizawishlist.wishlist.data.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class WishlistRequestModel {
	
	@NotBlank
	@Size(min = 24, max = 24, message = "The size of clientId should be 24")
	@Pattern(regexp = "^[0-9a-fA-F]{24}$", message = "clientId is not an valid ObjectId pattern")
	private String clientId;
	@NotBlank
	@Size(min = 24, max = 24, message = "The size of produtoId should be 24")
	@Pattern(regexp = "^[0-9a-fA-F]{24}$", message = "clientId is not an valid ObjectId pattern")
	private String productId;
	
	public WishlistRequestModel(String clientId, String productId) {
		this.clientId = clientId;
		this.productId = productId;
	}
	
	
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	
	
//	public String getClientId() {
//		return clientId.toHexString();
//	}
//	public void setClientId(String clientId) {
//		this.clientId = new ObjectId(clientId);
//	}
//	public String getProductId() {
//		return productId.toHexString();
//	}
//	public void setProductId(String productId) {
//		this.productId = new ObjectId(productId);
//	}
	
	
	
	


}
