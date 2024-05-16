package com.br.api.luizawishlist.cucumber.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import com.br.api.luizawishlist.cucumber.tests.util.MockWishlist;
import com.br.api.luizawishlist.wishlist.domain.entities.Product;
import com.br.api.luizawishlist.wishlist.domain.entities.WishlistPage;
import com.br.api.luizawishlist.wishlist.domain.repositories.IWishlistRepository;
import com.br.api.luizawishlist.wishlist.domain.usecases.GetAllProductsWishlistClient;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class WishlistGetAllProductsSteps {

	private final IWishlistRepository wishlistRepository;
	private final GetAllProductsWishlistClient getAllProductsWishlistClient;
	private final MockWishlist mockWishlist;

	private Exception caughtException;
	private List<Product> listProducts;

	public WishlistGetAllProductsSteps(IWishlistRepository wishlistRepository,
			GetAllProductsWishlistClient getAllProductsWishlistClient, MockWishlist mockWishlist) {
		this.wishlistRepository = wishlistRepository;
		this.getAllProductsWishlistClient = getAllProductsWishlistClient;
		this.mockWishlist = mockWishlist;
	}
	
    @Before
	public void beforeSetup() throws Exception {
		caughtException = null;
		listProducts = new ArrayList<Product>();
	}
	
	
	@Given("a client with ID {string} with an wishlist which has {string} products")
	public void aClientWithIdWithAnWishlistWhichHasProducts(String clientId, String qtyProducts) {
		var wishlist = mockWishlist.createFullWishlist(clientId, Integer.valueOf(qtyProducts));
		wishlistRepository.saveWishlistProduct(wishlist);
	}
	
	@Given("a client with ID {string} with no wishlist to find all")
	public void aClientWithIdWithNoWishlistToFindAll(String clientId) {
		assertFalse(wishlistRepository.existsByClientId(clientId));
	}
	
	
	@When("I try to get the products from the client ID {string} from page {string}")
	public void iTryToGetTheProductsFromTheClientIdFromPage(String clientId, String page) {
		try {
			listProducts = getAllProductsWishlistClient.call(new WishlistPage(clientId, Integer.valueOf(page)));
		} catch (Exception e) {
			caughtException = e;
		}
	}
	@Then("should return to me {string} products")
	public void shouldReturnToMeProducts(String qtyProducts) {
		assertNotNull(listProducts);
		assertEquals(listProducts.size(), Integer.valueOf(qtyProducts));
	}
	
	@Then("after trying searching all should give me an exception saying {string}")
	public void afterTryingSearchingAllShouldGiveMeAnExceptionSaying(String expectedMessage) {
		assertEquals(expectedMessage, caughtException.getMessage());
	}
	
	//There is no withlist to this client to find all
	//There is no withlist to this client to find all
	
	
}
