package pl.edu.pjatk.tau.labone.service;

import java.math.BigDecimal;
import java.util.List;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pl.edu.pjatk.tau.labone.domain.Product;
import static org.junit.Assert.assertEquals;

public class ProductStepdefs {

	private Product product;
	private String answer;
	private OrderService orderService;
	private List<Product> filteredProducts;
	private int seq = 0;

	@Given("^we have a new product$")
	public void we_have_a_new_product() throws Exception {
		product = new Product(++this.seq, "", BigDecimal.ZERO);
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

	@Given("^we have a list of products$")
	public void we_have_a_product_list() throws Exception {
		this.orderService = new OrderServiceImpl(new DateServiceImpl());

	}

	@Given("^we set the product name:\"([^\"]*)\", price:(\\d+\\.\\d{2})$")
	public void we_set_the_product_name_and_price(String name, double price) throws Exception {
		this.product.setName(name);
		this.product.setPrice(BigDecimal.valueOf(price));
	}

	@When("^we add ([0-9]*) products to the list$")
	public void we_add_some_products_to_the_list(int number) throws Exception {
		for(int i = 0; i < number; i++) {
			we_have_a_new_product();
			this.orderService.createProduct(this.product);
		}
	}

	@When("^we add this product to the list$")
	public void we_add_the_created_product_to_the_list() throws Exception {
		this.orderService.createProduct(this.product);
	}

	@Given("^we create product with id ([0-9]*)$")
	public void we_create_product_with_id(int id) throws Exception {
		this.product = new Product(id);
	}

	@When("^we remove products with id ([0-9]*) and ([0-9]*)$")
	public void we_remove_products_with_id_and(int id1, int id2) throws Exception {
		this.orderService.deleteProduct(id1);
		this.orderService.deleteProduct(id2);
	}

	@Then("^the list of products should count ([0-9]*)$")
	public void the_list_of_products_should_count(int count) throws Exception {
		assertEquals(count, this.orderService.getAllProducts().size());
	}

	@Given("^we create product with id ([0-9]*) and name \"([^\"]*)\"$")
	public void we_create_product_with_id_and_name(int id, String name) throws Exception {
		this.product = new Product(id, name);
	}

	@When("^we search for product by regexp \"([^\"]*)\"$")
	public void we_search_for_product_by_regexp(String regexp) throws Exception {
		this.filteredProducts = this.orderService.getProductsByRegexp(regexp);
	}

	@Then("^the filtered list of products should count ([0-9]*)$")
	public void the_filtered_list_of_products_should_count(int count) throws Exception {
		assertEquals(count, this.filteredProducts.size());
	}
}
