Feature: Wishlist creation

  Scenario: Create a new wishlist when none exists for a client
    Given a client with ID "5e3fabcd6789123456789013" does not have a wishlist
    When I try to create a wishlist for the client with ID "5e3fabcd6789123456789013" and product ID "5e2fbd88db12345678901245"
    Then a new wishlist should be created for the client ID "5e3fabcd6789123456789013" with no errors
    
  Scenario: Create a new wishlist when one already exists for a client and tried to add a duplicate product
    Given a client with ID "5e3fabcd6789123456789013" that has a wishlist with the product ID "5e2fbd88db12345678901245"
    When I try to create a wishlist for the client with ID "5e3fabcd6789123456789013" and product ID "5e2fbd88db12345678901245"
    Then after trying saving should give me an exception saying "Product already exists in this wishlist"
    
  Scenario: Create a new wishlist when one already exists for a client and it already has the limit od products
    Given a client with ID "5e3fabcd6789123456789013" that has a limit of products
    When I try to create a wishlist for the client with ID "5e3fabcd6789123456789013" and product ID "5e2fbd88db12345678901245"
    Then after trying saving should give me an exception saying "Wishlist already reached the limit of products: 20"
    
  Scenario: Create a new wishlist when one already exists for a client
    Given a client with ID "5e3fabcd6789123456789013" that has a wishlist with the product ID "5e2fbd88db12345678901245"
    When I try to create a wishlist for the client with ID "5e3fabcd6789123456789013" and product ID "5e2fbd88db12345678901246"
    Then a new wishlist should be created for the client ID "5e3fabcd6789123456789013" with no errors

 
