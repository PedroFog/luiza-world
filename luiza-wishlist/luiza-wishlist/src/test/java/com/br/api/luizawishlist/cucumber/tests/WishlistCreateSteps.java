package com.br.api.luizawishlist.cucumber.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.bson.types.ObjectId;

import com.br.api.luizawishlist.cucumber.tests.util.MockWishlist;
import com.br.api.luizawishlist.wishlist.data.converter.WishlistConverter;
import com.br.api.luizawishlist.wishlist.data.models.WishlistRequestModel;
import com.br.api.luizawishlist.wishlist.domain.entities.Product;
import com.br.api.luizawishlist.wishlist.domain.entities.Wishlist;
import com.br.api.luizawishlist.wishlist.domain.repositories.IWishlistRepository;
import com.br.api.luizawishlist.wishlist.domain.usecases.CreateWishlistProduct;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class WishlistCreateSteps {

	private final IWishlistRepository wishlistRepository;
	private final CreateWishlistProduct createWishlistProduct;
	private final WishlistConverter wishlistConverter;
	private final MockWishlist mockWishlist;

	private Exception caughtException;

	@Before
	public void beforeSetup() {
		caughtException = null;
	}

	public WishlistCreateSteps(IWishlistRepository wishlistRepository, CreateWishlistProduct createWishlistProduct,
			WishlistConverter wishlistConverter, MockWishlist mockWishlist) {
		this.wishlistRepository = wishlistRepository;
		this.createWishlistProduct = createWishlistProduct;
		this.wishlistConverter = wishlistConverter;
		this.mockWishlist = mockWishlist;
	}

	@Given("a client with ID {string} does not have a wishlist")
	public void givenClientDoesNotHaveAWishlist(String clientId) {
		assertFalse(wishlistRepository.existsByClientId(clientId));
	}

	@Given("a client with ID {string} that has a limit of products")
	public void givenClientHasLimitOfProducts(String clientId) {
		Wishlist wishlist = mockWishlist.createFullWishlist(clientId, 20);
		wishlistRepository.saveWishlistProduct(wishlist);
	}

	@Given("a client with ID {string} that has a wishlist with the product ID {string}")
	public void givenClientHasAWishlistWithProduct(String clientId, String productId) {
		Wishlist wishlist = new Wishlist(clientId);
		wishlist.setProducts(Arrays.asList(new Product(new ObjectId(productId))));
		wishlistRepository.saveWishlistProduct(wishlist);
	}

	@When("I try to create a wishlist for the client with ID {string} and product ID {string}")
	public void whenITryToCreateAWishlist(String clientId, String productId) {
		var testWishlist = wishlistConverter.toWishlist(new WishlistRequestModel(clientId, productId));
		try {
			createWishlistProduct.call(testWishlist);
		} catch (Exception e) {
			caughtException = e;
		}
	}

	@Then("a new wishlist should be created for the client ID {string} with no errors")
	public void thenANewWishlistShouldBeCreatedWithNoErrors(String clientId) {
		assertNull(caughtException);
		assertTrue(wishlistRepository.existsByClientId(clientId));
	}

	@Then("after trying saving should give me an exception saying {string}")
	public void afterTryingSavingShouldGiveMeAnExceptionSaying(String expectedMessage) {
		assertNotNull(caughtException);
		assertEquals(expectedMessage, caughtException.getMessage());
	}

}
