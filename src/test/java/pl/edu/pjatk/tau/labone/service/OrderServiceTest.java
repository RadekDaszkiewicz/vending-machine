/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.pjatk.tau.labone.service;

import java.math.BigDecimal;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import pl.edu.pjatk.tau.labone.domain.Product;
import pl.edu.pjatk.tau.labone.exception.DuplicatedIdException;
import pl.edu.pjatk.tau.labone.exception.ProductNotFoundException;

/**
 *
 * @author s14646
 */
public class OrderServiceTest {
    
    private OrderService orderService;
    
    @Before
    public void before() {
        orderService = new OrderService();
    }
    
    private void addTestProducts() {
        Product p1 = new Product(1, "Produkt", BigDecimal.valueOf(1.00));
        orderService.addProduct(p1);
        Product p2 = new Product(2, "Drugi rodukt", BigDecimal.valueOf(2.00));
        orderService.addProduct(p2);
        Product p3 = new Product(3, "Trzeci rodukt", BigDecimal.valueOf(3.00));
        orderService.addProduct(p3);
    }
    
    @Test(expected = DuplicatedIdException.class)
    public void testCreateUniqueProduct() {
        Product p1 = new Product(1, "Produkt", BigDecimal.valueOf(3.00));
        orderService.addProduct(p1);
        Product p2 = new Product(1, "Produkt", BigDecimal.valueOf(3.00));
        orderService.addProduct(p2);   
    }
    
    @Test
    public void testReadAll() {
        addTestProducts();
        assertEquals(orderService.getAllProducts().size(), 3);
    }
    
    @Test
    public void testRead() {
        addTestProducts();
        Product p = orderService.getProductById(1);
        Product p2 = new Product(1, "Produkt", BigDecimal.valueOf(1.00));
        assertTrue(p.equals(p2));
    }
    
    @Test(expected = ProductNotFoundException.class)
    public void testUnexistingProductRead() {
        addTestProducts();
        orderService.getProductById(99);
    }
    
    @Test
    public void testUpdateProduct() {
        addTestProducts();
        Product p = orderService.getProductById(3);
        p.setPrice(BigDecimal.valueOf(100.0));
        orderService.updateProduct(p);
        assertEquals(p, new Product(3, "Trzeci rodukt", BigDecimal.valueOf(100.0)));
    }
}
