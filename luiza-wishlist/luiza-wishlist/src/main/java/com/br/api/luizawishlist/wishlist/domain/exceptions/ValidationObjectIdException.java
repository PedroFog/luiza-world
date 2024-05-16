package com.br.api.luizawishlist.wishlist.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidationObjectIdException extends RuntimeException {

	public ValidationObjectIdException(String ex) {
		super(ex);
	}

	private static final long serialVersionUID = 1L;

}