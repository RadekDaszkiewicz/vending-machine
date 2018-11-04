package pl.edu.pjatk.tau.labone.service;

import pl.edu.pjatk.tau.labone.domain.Product;
import pl.edu.pjatk.tau.labone.exception.DuplicatedIdException;
import pl.edu.pjatk.tau.labone.exception.ProductNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class OrderServiceTest {

    private DateService dateServiceMock = mock(DateService.class);
    private OrderService orderService;

    @Before
    public void before() {
        orderService = new OrderServiceImpl(dateServiceMock);
        Product p1 = new Product(1, "Produkt", BigDecimal.valueOf(1.00));
        orderService.getRepository().add(p1);
        Product p2 = new Product(2, "Drugi rodukt", BigDecimal.valueOf(2.00));
        orderService.getRepository().add(p2);
        Product p3 = new Product(3, "Trzeci rodukt", BigDecimal.valueOf(3.00));
        orderService.getRepository().add(p3);
    }

    @Test(expected = DuplicatedIdException.class)
    public void testCreateDuplicatedProduct() {
        Product p1 = new Product(1, "Produkt", BigDecimal.valueOf(1.00));
        orderService.createProduct(p1);
    }

    @Test
    public void testReadAll() {
        assertEquals(orderService.getAllProducts().size(), 3);
    }

    @Test(expected = ProductNotFoundException.class)
    public void testNonexistentProductRead() {
        orderService.getProductById(99);
    }

    @Test
    public void testUpdateProduct() {
        orderService.updateProduct(new Product(3, "Zmieniony produkt", BigDecimal.valueOf(100.0)));
        assertEquals(new Product(3, "Zmieniony produkt", BigDecimal.valueOf(100.0)), orderService.getProductById(3));
    }

    @Test(expected = ProductNotFoundException.class)
    public void testDeleteProduct() {
        orderService.deleteProduct(2);
        orderService.getProductById(2);
    }

    @Test
    public void testCreateProductDate() {
        LocalDate mockedDate = LocalDate.parse("2018-10-21");
        when(dateServiceMock.getDate()).thenReturn(mockedDate);
        Product p = new Product(4, "Produkt 4", BigDecimal.valueOf(4.0));
        orderService.createProduct(p);
        p = orderService.getProductById(4);
        assertEquals(p.getAddDate(), mockedDate);
    }

    @Test
    public void testCreateProductWithoutDate() {
        orderService.setCreateAddDate(false);
        Product p = new Product(5, "Produkt 5", BigDecimal.valueOf(5.0));
        orderService.createProduct(p);
        p = orderService.getProductById(5);
        assertNull(p.getAddDate());
    }

}