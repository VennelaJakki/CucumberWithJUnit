Feature: Accounts Page

  @accounts @regression @smoke
  Scenario: Verify the headers on the accounts page
    Given the user is logged in and on the accounts page
    When the user checks the page headers
    Then the page headers should be:
      | My Account           |
      | My Orders            |
      | My Affiliate Account |
      | Newsletter           |

  @accounts @regression
  Scenario Outline: Verify the search functionality on the accounts page
    Given the user is logged in and on the accounts page
    When the user searches for "<searchKey>"
    Then the result count should be <resultsCount>
    Examples:
      | searchKey | resultsCount |
      | macbook   | 3            |
      | imac      | 1            |
      | samsung   | 2            |
      | Airtel    | 0            |

  @accounts @regression @image
  Scenario Outline: Verify the image count on the search results page
    Given the user is logged in and on the accounts page
    When the user searches for "<searchKey>"
    When the user selects the product "<productName>"
    Then the image count should be <imageCount>
    Examples:
      | searchKey | productName              | imageCount |
      | macbook   | MacBook Pro              | 4          |
      | imac      | iMac                     | 3          |
      | samsung   | Samsung SyncMaster 941BW | 1          |

  @accounts @regression
  Scenario Outline: Verify the product info on the product info page
    Given the user is logged in and on the accounts page
    When the user searches for "<searchKey>"
    When the user selects the product "<productName>"
    Then the product header should be "<productName>"
    And the product code should be "<productCode>"
    And the availability should be "<availability>"
    Then the price should be "<price>"
    Then the ExTax should be "<ex Tax>"
    Examples:
      | searchKey | productName              | productCode | availability | price     | ex Tax    |
      | macbook   | MacBook Pro              | Product 18  | Out Of Stock | $2,000.00 | $2,000.00 |
      | imac      | iMac                     | Product 14  | Out Of Stock | $122.00   | $100.00   |
      | samsung   | Samsung SyncMaster 941BW | Product 6   | 2-3 Days     | $242.00   | $200.00   |

