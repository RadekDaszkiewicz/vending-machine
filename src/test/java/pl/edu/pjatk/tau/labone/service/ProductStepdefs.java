package pl.edu.pjatk.tau.labone.service;

import java.math.BigDecimal;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pl.edu.pjatk.tau.labone.domain.Product;
import static org.junit.Assert.assertEquals;

public class ProductStepdefs {

	private Product product;
	private String answer;

	@Given("^we have a product$")
	public void we_have_a_product() throws Exception {
		product = new Product();
	}

	@Given("^product price is (\\d+\\.\\d{2})$")
	public void product_price_is(Double price) throws Exception {
		product.setPrice(BigDecimal.valueOf(price));
	}

	@When("^I ask whether it is free$")
	public void i_ask_whether_it_is_free() throws Exception {
		answer = product.getPrice().doubleValue() == 0.00 ? "Yes" : "No";
	}

	@Then("^I should be told \"([^\"]*)\"$")
	public void i_should_be_told(String expectedAnswer) throws Exception {
		assertEquals(expectedAnswer, answer);
	}

}
