package pl.edu.pjatk.tau.labone.service;

import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Test;
import pl.edu.pjatk.tau.labone.domain.Product;
import pl.edu.pjatk.tau.labone.exception.DuplicatedIdException;
import static org.junit.Assert.assertEquals;

public class OrderServiceTest {

	private OrderService orderService = new OrderServiceImpl();

	@Before
	public void before() {
		Product p1 = new Product(1, "Produkt", BigDecimal.valueOf(1.00));
		orderService.getRepository().add(p1);
		Product p2 = new Product(2, "Drugi rodukt", BigDecimal.valueOf(2.00));
		orderService.getRepository().add(p2);
		Product p3 = new Product(3, "Trzeci rodukt", BigDecimal.valueOf(3.00));
		orderService.getRepository().add(p3);
	}

	@Test(expected = DuplicatedIdException.class)
	public void testCreateProduct() {
		Product p1 = new Product(1, "Produkt", BigDecimal.valueOf(1.00));
		orderService.createProduct(p1);
	}

	@Test
	public void testReadAll() {
		assertEquals(orderService.getAllProducts().size(), 3);
	}

}