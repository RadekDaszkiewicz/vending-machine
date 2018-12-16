package pl.edu.pjatk.tau.labone.service;

import pl.edu.pjatk.tau.labone.domain.Order;
import pl.edu.pjatk.tau.labone.domain.Product;

import java.util.Collection;
import java.util.List;

public interface OrderManager {

    void addProduct(Product p);

    Collection<Product> getAllProducts();

    void deleteProduct(Product p);

    Product getProductById(Integer id);

    void updateProduct(Product p);

    Integer addNewOrder(Order o);

    void addProductToOrder(Product p, Order o);

    void removeProductFromOrder(Product p, Order o);

    Order getOrderById(Integer id);

    List<Order> getAllOrders();
}
