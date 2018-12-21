package pl.edu.pjatk.tau.labone.service;

import java.math.BigDecimal;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
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
import static org.junit.Assert.assertNotNull;

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

//@DatabaseSetup("/fullData.xml")
//	@ExpectedDatabase(value = "/addProductData.xml",
//			assertionMode = DatabaseAssertionMode.NON_STRICT)
	@Test
	public void addAndGetOrder() throws ParseException {
		assertEquals(0, this.orderManager.getAllOrders().size());
		Integer orderId = this.orderManager.addNewOrder(new Cart());
		assertEquals(1, this.orderManager.getAllOrders().size());
		Cart o = this.orderManager.getOrderById(orderId);
		assertNotNull(o);
	}

	@Test
	public void addAndGetProduct() {
		assertEquals(0, this.orderManager.getAllProducts().size());
		Integer productId = this.orderManager.createProduct(new Product("produkt", BigDecimal.valueOf(1)));
		assertEquals(1, this.orderManager.getAllProducts().size());
		Product p = this.orderManager.getProductById(productId);
		assertEquals("produkt", p.getName());
		assertEquals(BigDecimal.ONE, p.getPrice());
	}

	@Test
	public void addProductToOrder() {
		Integer orderId = this.orderManager.addNewOrder(new Cart());
		Cart o = this.orderManager.getOrderById(orderId);
		assertEquals(0, o.getProducts().size());
		this.orderManager.addProductToOrder(new Product("p", BigDecimal.ONE), o);
		o = this.orderManager.getOrderById(orderId);
		assertEquals(1, o.getProducts().size());
	}

	@Test
	public void removeOrder() {
		Integer id1 = this.orderManager.addNewOrder(new Cart());
		Integer id2 = this.orderManager.addNewOrder(new Cart());
		Integer id3 = this.orderManager.addNewOrder(new Cart());
		this.orderManager.deleteOrder(this.orderManager.getOrderById(id2));
		assertEquals(this.orderManager.getAllOrders().size(), 2);
	}

	@Test
	public void removeProductFromOrder() {

	}

//	@Test
//	public void findAllProducts() {
//		assertEquals(5, this.orderManager.getAllProducts().size());
//		Product p = new Product("stworzony przez hibernate", BigDecimal.valueOf(5));
//		this.orderManager.addProduct(p);
//		assertEquals(6, this.orderManager.getAllProducts().size());
//	}

}
