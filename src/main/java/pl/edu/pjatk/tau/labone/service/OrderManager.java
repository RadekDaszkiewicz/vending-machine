package pl.edu.pjatk.tau.labone.service;

import java.util.Collection;
import java.util.List;
import pl.edu.pjatk.tau.labone.domain.Cart;
import pl.edu.pjatk.tau.labone.domain.Product;

public interface OrderManager {

    int createProduct(Product p);

    Collection<Product> getAllProducts();

    void deleteProduct(Product p);

    Product getProductById(Integer id);

    void updateProduct(Product p);

    Integer addNewOrder(Cart o);

    void addProductToOrder(Product p, Cart o);

    void removeProductFromOrder(Product p, Cart o);

    Cart getOrderById(Integer id);

    List<Cart> getAllOrders();

    void deleteOrder(Cart c);

    Collection<Product> findProductsByName(String name);
}
