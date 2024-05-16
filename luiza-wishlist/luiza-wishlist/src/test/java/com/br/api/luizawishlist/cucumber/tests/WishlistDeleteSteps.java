package com.br.api.luizawishlist.cucumber.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.bson.types.ObjectId;

import com.br.api.luizawishlist.wishlist.domain.entities.Product;
import com.br.api.luizawishlist.wishlist.domain.entities.Wishlist;
import com.br.api.luizawishlist.wishlist.domain.repositories.IWishlistRepository;
import com.br.api.luizawishlist.wishlist.domain.usecases.DeleteWishlistProduct;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class WishlistDeleteSteps {

	private final IWishlistRepository wishlistRepository;
	private final DeleteWishlistProduct deleteWishlistProduct;

	private Exception caughtException;

	public WishlistDeleteSteps(IWishlistRepository wishlistRepository, DeleteWishlistProduct deleteWishlistProduct) {
		this.wishlistRepository = wishlistRepository;
		this.deleteWishlistProduct = deleteWishlistProduct;
	}
	
	@Before
	public void beforeSetup() {
		caughtException = null;
	}

	@Given("a client with ID {string} with an wishlist which has two products ID {string} and {string}")
	public void aClientWishIdWishAnWishlistWhichHasTwoProductsIdAnd(String clientId, String productIdOne,
			String productIdTwo) {
		Wishlist wishlist = new Wishlist(clientId);
		wishlist.setProducts(
				Arrays.asList(new Product(new ObjectId(productIdOne)), new Product(new ObjectId(productIdTwo))));
		wishlistRepository.saveWishlistProduct(wishlist);
	}

	@Given("a client with ID {string} with no wishlist")
	public void aClientWithIdWithNoWishlist(String clientId) {
		assertFalse(wishlistRepository.existsByClientId(clientId));
	}

	@Given("a client with ID {string} with an wishlist which has one product ID {string}")
	public void aClientWithIdWithAnWishlistWhichHasOneProductId(String clientId, String productId) {
		Wishlist wishlist = new Wishlist(clientId);
		wishlist.setProducts(
				Arrays.asList(new Product(new ObjectId(productId))));
		wishlistRepository.saveWishlistProduct(wishlist);
	}

	@When("I try to remove the product of ID {string} from the wishlist from the client ID {string}")
	public void iTryToRemoveTheProductOfIdFromTheWishlistFromTheClientId(String productId, String clientId) {
		try {
			deleteWishlistProduct.call(clientId, productId);
		} catch (Exception e) {
			caughtException = e;
		}

	}

	@Then("after trying removing should give me an exception saying {string}")
	public void afterTryingRemovingShouldGiveMeAnExceptionSaying(String expectedMessage) {
		assertNotNull(caughtException);
		assertEquals(expectedMessage, caughtException.getMessage());
	}

	@Then("the wishlist from the client ID {string} should have {string} products now")
	public void theWishlistFromTheClientIdShouldHaveProductsNow(String clientId, String qtd) {
		assertNull(caughtException);
		assertTrue(wishlistRepository.existsByClientId(clientId));
		var wishlist = wishlistRepository.findByClientId(clientId);
		assertEquals(Integer.valueOf(wishlist.getProducts().size()), Integer.valueOf(qtd));
	}

	@Then("the wishlist from the client ID {string} should not exists")
	public void theWishlistFromTheClientIdShouldNotExists(String clientId) {
		assertFalse(wishlistRepository.existsByClientId(clientId));
	}
}
