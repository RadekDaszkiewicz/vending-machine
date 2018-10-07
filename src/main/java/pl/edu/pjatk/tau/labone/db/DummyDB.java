/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.pjatk.tau.labone.db;

import java.util.ArrayList;
import java.util.List;
import pl.edu.pjatk.tau.labone.domain.Product;
import pl.edu.pjatk.tau.labone.exception.DuplicatedIdException;
import pl.edu.pjatk.tau.labone.exception.ProductNotFoundException;

/**
 *
 * @author s14646
 */
public class DummyDB {

    private List<Product> products;

    public DummyDB() {
        products = new ArrayList<>();
    }

    public void addProduct(Product p1) {
        for (Product p : products) {
            if (p.getId() == p1.getId()) {
                throw new DuplicatedIdException();
            }
        }
        products.add(p1);
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public Product getProductById(int id) {
        for (Product p : products) {
            if (p.getId() == id) {
                return p;
            }
        }
        throw new ProductNotFoundException();
    }

    public void updateProduct(Product p) {
        Product p1 = getProductById(p.getId());
        p1 = p;
    }
}
