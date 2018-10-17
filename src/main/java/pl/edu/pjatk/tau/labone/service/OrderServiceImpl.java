package pl.edu.pjatk.tau.labone.service;

import java.util.ArrayList;
import java.util.List;
import pl.edu.pjatk.tau.labone.domain.Product;
import pl.edu.pjatk.tau.labone.exception.DuplicatedIdException;

public class OrderServiceImpl implements OrderService {

	private List<Product> repository = new ArrayList<>();

	@Override
	public List<Product> getRepository() {
		return this.repository;
	}

	@Override
	public void createProduct(Product p1) {
		for (Product p : repository) {
			if (p.getId() == p1.getId()) {
				throw new DuplicatedIdException();
			}
		}
		repository.add(p1);
	}
}