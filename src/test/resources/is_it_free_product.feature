Feature: Is it free product?
  Everyone wants to know if it's a free product

  Scenario Outline: A product with a given price is free or not
    Given we have a product
    And product price is <price>
    When I ask whether it is free
    Then I should be told <answer>

    Examples:
      | price | answer |
      | 0.00  | "Yes"  |
      | 1.00  | "No"   |

