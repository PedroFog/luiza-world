package com.br.api.luizawishlist.wishlist.application.handler;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.br.api.luizawishlist.wishlist.domain.exceptions.NotMoreProductsInWishListException;
import com.br.api.luizawishlist.wishlist.domain.exceptions.ValidationObjectIdException;
import com.br.api.luizawishlist.wishlist.domain.exceptions.WishlistMaxCapacityException;
import com.br.api.luizawishlist.wishlist.domain.exceptions.WishlistNotFoundException;
import com.br.api.luizawishlist.wishlist.domain.exceptions.WishlistProductAlreadyExistsInException;
import com.br.api.luizawishlist.wishlist.domain.exceptions.WishlistProductNotFoundInException;

@RestControllerAdvice
public class CustomizedResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(WishlistMaxCapacityException.class)
	public final ResponseEntity<ExceptionResponse> handleWishlistMaxCapacityExceptions(Exception ex,
			WebRequest request) {

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(WishlistProductAlreadyExistsInException.class)
	public final ResponseEntity<ExceptionResponse> handleWishlistProductAlreadyExistsInExceptions(Exception ex,
			WebRequest request) {

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(WishlistNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleWishlistNotFoundExceptions(Exception ex, WebRequest request) {

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(WishlistProductNotFoundInException.class)
	public final ResponseEntity<ExceptionResponse> handleWishlistProductNotFoundInExceptions(Exception ex,
			WebRequest request) {

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ValidationObjectIdException.class)
	public final ResponseEntity<ExceptionResponse> handleValidationObjectIdExceptions(Exception ex,
			WebRequest request) {

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	

	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			WebRequest request) {
		List<String> errors = ex.getBindingResult().getAllErrors().stream().map(this::formatErrorMessage)
				.collect(Collectors.toList());

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Validation failed",
				String.join(", ", errors));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NotMoreProductsInWishListException.class)
	public final ResponseEntity<ExceptionResponse> handleNotMoreProductsInWishListExceptions(Exception ex,
			WebRequest request) {

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	

	private String formatErrorMessage(ObjectError error) {
		if (error instanceof FieldError) {
			FieldError fieldError = (FieldError) error;
			return fieldError.getField() + ": " + fieldError.getDefaultMessage();
		} else {
			return error.getObjectName() + ": " + error.getDefaultMessage();
		}
	}
}
