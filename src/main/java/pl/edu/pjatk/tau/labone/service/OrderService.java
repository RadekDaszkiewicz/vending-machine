/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.pjatk.tau.labone.service;

import java.util.List;
import pl.edu.pjatk.tau.labone.db.DummyDB;
import pl.edu.pjatk.tau.labone.domain.Product;

/**
 *
 * @author s14646
 */
public class OrderService {
    private DummyDB db;
    
    public OrderService() {
        db = new DummyDB();
    }

    public void addProduct(Product p) {
        db.addProduct(p);
    }
    
    public List<Product> getAllProducts() {
        return db.getAllProducts();
    }

    public Product getProductById(int id) {
        return db.getProductById(id);
    }

    void updateProduct(Product p) {
        db.updateProduct(p);
    }
    
}
