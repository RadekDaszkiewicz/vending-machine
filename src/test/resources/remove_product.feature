Feature: Adding and removing products from the list

  Scenario Outline: Add product to the list
    Given we have a list of products
    When we add <number> products to the list
    Then the list of products should count <count>

    Examples:
      | number | count |
      |   0    |   0   |
      |   1    |   1   |
      |   2    |   2   |

  Scenario Outline: Remove product from the list
    Given we have a list of products
    And we create product with id <1st id>
    And we add this product to the list
    And we create product with id <2nd id>
    And we add this product to the list
    When we remove products with id <id to remove> and <2nd id to remove>
    Then the list of products should count <count>

    Examples:
      | 1st id | 2nd id | id to remove | 2nd id to remove | count |
      |    1   |    2   |      1       |        2         |   0   |
      |    1   |    2   |      1       |        1         |   1   |
      |    1   |    2   |      0       |        0         |   2   |
      |    1   |    2   |      2       |        1         |   0   |