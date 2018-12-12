package pl.edu.pjatk.tau.labone.service;

import pl.edu.pjatk.tau.labone.domain.Product;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ProductService {
    Connection getConnection();

    void setConnection(Connection connection) throws SQLException;

    int addProduct(Product p);

    int deleteProduct(Product p);

    int updateProduct(Product p) throws SQLException;

    Product getProduct(int id) throws SQLException;

    int deleteAll();

    List<Product> getAll();
}
