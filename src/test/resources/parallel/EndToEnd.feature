Feature:End To End Scenarios
  This feature file contains end to scenarios related to opencart application

  Background:User should be logged in
    Given the user is logged in to the application


    @E2E
    Scenario:End to End flow of a product
      When the user searches for product "macbook"
      Then the search result count should be 3
      And the user selects product "MacBook Pro"
      Then the product image count should be 4
      Then the selected product header should be "MacBook Pro"
      When the user adds the product to cart
      Then user validates add to cart message "Success: You have added MacBook Pro to your shopping cart!"
      When user goes to shopping cart page
      Then user should see "Shopping Cart  (0.00kg)" as header
      And user should see product name as "MacBook Pro"
      And user should see total price as "$2,000.00"
