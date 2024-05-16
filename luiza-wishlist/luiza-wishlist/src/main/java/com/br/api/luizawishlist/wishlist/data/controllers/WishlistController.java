package com.br.api.luizawishlist.wishlist.data.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.api.luizawishlist.wishlist.data.converter.ProductConverter;
import com.br.api.luizawishlist.wishlist.data.converter.WishlistConverter;
import com.br.api.luizawishlist.wishlist.data.models.ProductPageRequestModel;
import com.br.api.luizawishlist.wishlist.data.models.WishlistRequestModel;
import com.br.api.luizawishlist.wishlist.domain.usecases.CreateWishlistProduct;
import com.br.api.luizawishlist.wishlist.domain.usecases.DeleteWishlistProduct;
import com.br.api.luizawishlist.wishlist.domain.usecases.GetAllProductsWishlistClient;
import com.br.api.luizawishlist.wishlist.domain.usecases.GetProductWishlistClient;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/wishlists/v1")
@Tag(name = "Wishlists", description = "Endpoints for managing wishlists")
public class WishlistController {

	private final WishlistConverter wishlistConverter;
	private final ProductConverter productConverter;

	private final CreateWishlistProduct createWishlistProduct;
	private final DeleteWishlistProduct deleteWishlistProduct;
	private final GetProductWishlistClient getProductWishlistClient;
	private final GetAllProductsWishlistClient getAllProductsWishlistClient;

	WishlistController(WishlistConverter wishlistConverter, CreateWishlistProduct createWishlistProduct,
			DeleteWishlistProduct deleteWishlistProduct, GetProductWishlistClient getProductWishlistClient,
			GetAllProductsWishlistClient getAllProductsWishlistClient, ProductConverter productConverter) {
		this.createWishlistProduct = createWishlistProduct;
		this.wishlistConverter = wishlistConverter;
		this.deleteWishlistProduct = deleteWishlistProduct;
		this.getProductWishlistClient = getProductWishlistClient;
		this.getAllProductsWishlistClient = getAllProductsWishlistClient;
		this.productConverter = productConverter;
	}

	@Operation(summary = "Creates a new wishlist or product inside one", description = "Creates a new wishlist or product inside one", tags = {
			"Wishlists" }, responses = { @ApiResponse(description = "Created", responseCode = "200"),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content) })
	@PostMapping()
	public ResponseEntity<?> createWishlist(@RequestBody @Valid WishlistRequestModel requestModel) throws Exception  {
		createWishlistProduct.call(wishlistConverter.toWishlist(requestModel));
		return ResponseEntity.ok().build();
	}

	@Operation(summary = "Removes a product from an existing wishlist", description = "Removes a product from an existing wishlist", tags = {
			"Wishlists" }, responses = { @ApiResponse(description = "No content", responseCode = "204"),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content) })
	@DeleteMapping("{clientId}/{productId}")
	public ResponseEntity<?> deleteWishlist(@PathVariable(name = "clientId", required = true) String clientId,
			@PathVariable(name = "productId", required = true) String productId)  {
		deleteWishlistProduct.call(clientId, productId);
		return ResponseEntity.noContent().build();
	}

	@Operation(summary = "Checks if a product exists in an wishlist of a client", description = "Checks if a product exists in an wishlist of a client", tags = {
			"Wishlists" }, responses = { @ApiResponse(description = "Success", responseCode = "200"),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content) })
	@GetMapping("{clientId}/{productId}")
	public ResponseEntity<?> findIfProductExistsInWhislist(@PathVariable(name = "clientId", required = true) String clientId,
			@PathVariable(name = "productId", required = true) String productId)  {
		return ResponseEntity.ok(getProductWishlistClient.call(clientId, productId));
	}
	
	@Operation(summary = "Return all products from a client in pages of 5 itens", description = "Return all products from a client in pages of 5 itens", tags = {
	"Wishlists" }, responses = { @ApiResponse(description = "Success", responseCode = "200"),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content) })
	@PostMapping("/all")
	public ResponseEntity<?> findAllByClientId(@RequestBody @Valid ProductPageRequestModel requestModel)  {
		var allValues = getAllProductsWishlistClient.call(wishlistConverter.toWishlistPage(requestModel));
		return ResponseEntity.ok().body(productConverter.toProductPageResponse(allValues));
	}

}
