package com.br.api.luizawishlist.wishlist.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class WishlistProductNotFoundInException extends RuntimeException {

	public WishlistProductNotFoundInException(String ex) {
		super(ex);
	}

	private static final long serialVersionUID = 1L;

}