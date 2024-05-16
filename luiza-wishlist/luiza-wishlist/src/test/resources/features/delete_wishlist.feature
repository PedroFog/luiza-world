Feature: Wishlist product removal

  Scenario: Remove an product from an already existing wishlist
    Given a client with ID "5e3fabcd6789123456789013" with an wishlist which has two products ID "5e2fbd88db12345678901245" and "5e2fbd88db12345678901246"
    When I try to remove the product of ID "5e2fbd88db12345678901245" from the wishlist from the client ID "5e3fabcd6789123456789013"
    Then the wishlist from the client ID "5e3fabcd6789123456789013" should have "1" products now
    
  Scenario: Remove an product from an inexisting wishlist
    Given a client with ID "5e3fabcd6789123456789013" with no wishlist
    When I try to remove the product of ID "5e2fbd88db12345678901245" from the wishlist from the client ID "5e3fabcd6789123456789013"
    Then after trying removing should give me an exception saying "There is no withlist to this client to delete"
    
  Scenario: Remove an inexisting product from an already existing wishlist
    Given a client with ID "5e3fabcd6789123456789013" with an wishlist which has two products ID "5e2fbd88db12345678901245" and "5e2fbd88db12345678901246"
    When I try to remove the product of ID "5e2fbd88db12345678901248" from the wishlist from the client ID "5e3fabcd6789123456789013"
    Then after trying removing should give me an exception saying "This product do not exist in this wishlist to remove it"
    
  Scenario: Remove an product from an already existing wishlist with only 1 product
    Given a client with ID "5e3fabcd6789123456789013" with an wishlist which has one product ID "5e2fbd88db12345678901245"
    When I try to remove the product of ID "5e2fbd88db12345678901245" from the wishlist from the client ID "5e3fabcd6789123456789013"
    Then the wishlist from the client ID "5e3fabcd6789123456789013" should not exists