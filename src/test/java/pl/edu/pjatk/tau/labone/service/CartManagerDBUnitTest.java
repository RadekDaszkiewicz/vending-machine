package pl.edu.pjatk.tau.labone.service;

import java.math.BigDecimal;
import java.util.Collection;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pjatk.tau.labone.domain.Cart;
import pl.edu.pjatk.tau.labone.domain.Product;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/beans.xml"})
@Rollback
@Transactional(transactionManager = "txManager")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class,
		DbUnitTestExecutionListener.class})
public class CartManagerDBUnitTest {

	@Autowired
	OrderManager orderManager;

	@DatabaseSetup("/beforeAddOrder.xml")
	@ExpectedDatabase(value = "/addOrder.xml",
			assertionMode = DatabaseAssertionMode.NON_STRICT)
	@Test
	public void addOrder() throws ParseException {
		this.orderManager.addNewOrder(new Cart(3));
	}

	@DatabaseSetup("/fullData.xml")
	@ExpectedDatabase(value = "/afterOrderDeleted.xml",
			assertionMode = DatabaseAssertionMode.NON_STRICT)
	@Test
	public void deleteOrder() throws ParseException {
		this.orderManager.deleteOrder(this.orderManager.getOrderById(3));
	}

	@DatabaseSetup("/fullData.xml")
	@ExpectedDatabase(value = "/addProduct.xml",
			assertionMode = DatabaseAssertionMode.NON_STRICT)
	@Test
	public void addProduct() {
		this.orderManager.createProduct(new Product(9, "produkt", BigDecimal.ONE));
	}

	@DatabaseSetup("/fullData.xml")
	@ExpectedDatabase(value = "/afterProductUpdated.xml",
			assertionMode = DatabaseAssertionMode.NON_STRICT)
	@Test
	public void updateProduct() {
		Product p = this.orderManager.getProductById(8);
		p.setName("Zmieniony produkt");
		this.orderManager.updateProduct(p);

	}

	@DatabaseSetup("/fullData.xml")
	@ExpectedDatabase(value = "/afterProductDeleted.xml",
			assertionMode = DatabaseAssertionMode.NON_STRICT)
	@Test
	public void deleteProduct() {
		this.orderManager.deleteProduct(this.orderManager.getProductById(8));

	}

	@Test
	@DatabaseSetup("/fullData.xml")
	@ExpectedDatabase(value = "/afterAddProductToOrder.xml",
			assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void addProductToOrder() {
		this.orderManager.createProduct(new Product(9, "produkt", BigDecimal.ONE));
		Product p = this.orderManager.getProductById(9);
		Cart o = this.orderManager.getOrderById(3);
		this.orderManager.addProductToOrder(p, o);
	}

	@Test
	@DatabaseSetup("/fullData.xml")
	@ExpectedDatabase(value = "/afterRemoveProductFromOrder.xml",
			assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void removeProductFromOrder() {
		Cart order = this.orderManager.getOrderById(3);
		this.orderManager.removeProductFromOrder(this.orderManager.getProductById(7), order);
	}

	@Test
	@DatabaseSetup("/fullData.xml")
	public void findProductByName(){
		Collection<Product> products = this.orderManager.findProductsByName("produkt");
		assertEquals(4, products.size());
	}
}
