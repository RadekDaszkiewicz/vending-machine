Feature: Find product by name

  Scenario Outline: We find product by regexp
    Given we have a list of products
    And we create product with id <1st id> and name <1st name>
    And we add this product to the list
    And we create product with id <2nd id> and name <2nd name>
    And we add this product to the list
    When we search for product by regexp <regexp>
    Then the filtered list of products should count <count>

    Examples:
      | 1st id | 2nd id | 1st name       | 2nd name    | regexp            | count |
      |    1   |    2   |  "produkt 1"   | "produkt 2" | "produkt(.*)"     |   2   |
      |    1   |    2   |  "1 produkt"   | "produkt 2" | "produkt(.*)"     |   1   |
      |    1   |    2   |  "1 produkt"   | "produkt 2" | "(.*)produkt(.*)" |   2   |
      |    1   |    2   |  "produkt 1"   | "produkt 2" | "xxx"             |   0   |
      |    1   |    2   |  "produkt 1"   | "produkt"   | "produkt"         |   1   |