package pl.edu.pjatk.tau.labone.service;

import java.util.List;
import pl.edu.pjatk.tau.labone.domain.Product;

public interface OrderService {

	List<Product> getRepository();

	void createProduct(Product p);

	List<Product> getAllProducts();

	Product getProductById(int i);

	void updateProduct(Product p);

	void deleteProduct(int i);

	void setCreateAddDate(boolean createAddDate);

	void setCreateUpdateDate(boolean createUpdateDate);

	void setCreateReadDate(boolean createReadDate);

	List<Product> getProductsByRegexp(String regexp);
}