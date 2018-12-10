package pl.edu.pjatk.tau.labone.service;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import pl.edu.pjatk.tau.labone.domain.Product;

public interface OrderService {

	List<Product> getRepository();

	int createProduct(Product p);

	List<Product> getAllProducts();

	Product getProductById(int i);

	void updateProduct(Product p);

	void deleteProduct(int i);

	void setCreateAddDate(boolean createAddDate);

	void setCreateUpdateDate(boolean createUpdateDate);

	void setCreateReadDate(boolean createReadDate);

	List<Product> getProductsByRegexp(String regexp);
}