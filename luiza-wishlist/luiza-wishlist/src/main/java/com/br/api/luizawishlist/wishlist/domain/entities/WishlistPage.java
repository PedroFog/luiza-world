package com.br.api.luizawishlist.wishlist.domain.entities;

public class WishlistPage {

	private String clientId;
	private Integer page;
	
	public WishlistPage() {
	}
	
	public WishlistPage(String clientId, Integer page) {
		this.clientId = clientId;
		this.page = page;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}

}
