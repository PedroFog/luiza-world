package com.br.api.luizawishlist.cucumber.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;

import org.bson.types.ObjectId;

import com.br.api.luizawishlist.wishlist.domain.entities.Product;
import com.br.api.luizawishlist.wishlist.domain.entities.Wishlist;
import com.br.api.luizawishlist.wishlist.domain.repositories.IWishlistRepository;
import com.br.api.luizawishlist.wishlist.domain.usecases.GetProductWishlistClient;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class WishlistGetProductSteps {

	private final IWishlistRepository wishlistRepository;
	private final GetProductWishlistClient getProductWishlistClient;

	private Exception caughtException;
	private boolean productExists = false;

	public WishlistGetProductSteps(IWishlistRepository wishlistRepository,
			GetProductWishlistClient getProductWishlistClient) {
		this.wishlistRepository = wishlistRepository;
		this.getProductWishlistClient = getProductWishlistClient;
	}

	@Given("a client with ID {string} with an wishlist which has the product ID {string}")
	public void aClientWithIdWithAnWishlistWhichHasTheProductId(String clientId, String productId) {
		Wishlist wishlist = new Wishlist(clientId);
		wishlist.setProducts(
				Arrays.asList(new Product(new ObjectId(productId))));
		wishlistRepository.saveWishlistProduct(wishlist);
	}

	@When("I try to check the product of ID {string} from the wishlist from the client ID {string}")
	public void iTryToCheckTheProductOfIdFromTheWishListFromTheClientId(String productId, String clientId) {
		try {
			productExists = getProductWishlistClient.call(clientId, productId);
		} catch (Exception e) {
			caughtException = e;
		}
	}

	@Then("the check should be {string}")
	public void theCheckShouldBe(String check) {
		assertEquals(Boolean.valueOf(check), productExists);
	}

	@Then("after trying searching should give me an exception saying {string}")
	public void afterTryingSearchingShouldGiveMeAnExceptionSaying(String expectedMessage) {
		assertNotNull(caughtException);
		assertEquals(expectedMessage, caughtException.getMessage());

	}
}
