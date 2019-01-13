package pl.edu.pjatk.tau.labone.service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

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

import static org.junit.Assert.*;

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
		this.orderManager.getAllOrders().forEach(o -> System.out.println("######## " + o.getId()));
	}

	@DatabaseSetup("/fullData.xml")
	@ExpectedDatabase(value = "/addProduct.xml",
			assertionMode = DatabaseAssertionMode.NON_STRICT)
	@Test
	public void addProduct() {
//		assertEquals(0, this.orderManager.getAllProducts().size());
		Integer productId = this.orderManager.createProduct(new Product(10, "produkt", BigDecimal.ONE));
		this.orderManager.getAllProducts().forEach(p -> System.out.println("%%%%%%%%%%%% " + p.getId()));
//		assertEquals(1, this.orderManager.getAllProducts().size());
//		Product p = this.orderManager.getProductById(productId);
//		assertEquals("produkt", p.getName());
//		assertEquals(BigDecimal.ONE, p.getPrice());
	}

//	@Test
//	public void addProductToOrder() {
//		Integer orderId = this.orderManager.addNewOrder(new Cart());
//		Cart o = this.orderManager.getOrderById(orderId);
//		assertEquals(0, o.getProducts().size());
//		this.orderManager.addProductToOrder(new Product("p", BigDecimal.ONE), o);
//		o = this.orderManager.getOrderById(orderId);
//		assertEquals(1, o.getProducts().size());
//	}
//
//	@Test
//	public void removeOrder() {
//		Integer id1 = this.orderManager.addNewOrder(new Cart());
//		Integer id2 = this.orderManager.addNewOrder(new Cart());
//		Integer id3 = this.orderManager.addNewOrder(new Cart());
//		this.orderManager.deleteOrder(this.orderManager.getOrderById(id2));
//		assertEquals(2, this.orderManager.getAllOrders().size());
//	}
//
//	@Test
//	@DatabaseSetup("/fullData.xml")
//	public void removeProductFromOrder() {
//		Cart order = this.orderManager.getOrderById(3);
//		assertEquals(6, order.getProducts().size());
//		this.orderManager.removeProductFromOrder(this.orderManager.getProductById(3), order);
//		assertEquals(5, order.getProducts().size());
//	}
//
//	@Test
//	@DatabaseSetup("/fullData.xml")
//	public void findProductByName(){
//		Collection<Product> products = this.orderManager.findProductsByName("produkt");
//		assertEquals(4, products.size());
//	}
}
