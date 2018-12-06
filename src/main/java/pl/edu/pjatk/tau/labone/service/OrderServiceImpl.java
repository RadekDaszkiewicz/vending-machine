package pl.edu.pjatk.tau.labone.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pjatk.tau.labone.domain.Product;
import pl.edu.pjatk.tau.labone.exception.DuplicatedIdException;
import pl.edu.pjatk.tau.labone.exception.ProductNotFoundException;

@Component
public class OrderServiceImpl implements OrderService {

    private List<Product> repository = new ArrayList<>();
    @Autowired
    private DateService dateService;
    private boolean createAddDate = true;
    private boolean createUpdateDate = true;
    private boolean createReadDate = true;

    public OrderServiceImpl(DateService dateService) {
        this.dateService = dateService;
    }

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
        if (createAddDate) {
            p1.setAddDate(dateService.getDate());
        }
        repository.add(p1);
    }

    @Override
    public List<Product> getAllProducts() {
        repository.forEach(p -> {
            if (createReadDate) {
                p.setReadDate(dateService.getDate());
            }
        });
        return repository;
    }

    @Override
    public Product getProductById(int id) {
        for (Product p : repository) {
            if (p.getId() == id) {
                if (createReadDate){
                    p.setReadDate(dateService.getDate());
                }
                return p;
            }
        }
        throw new ProductNotFoundException();
    }

    @Override
    public void updateProduct(Product p) {
        Product p1 = getProductById(p.getId());
        p1.setName(p.getName());
        p1.setPrice(p.getPrice());
        if (createUpdateDate) {
            p1.setUpdateDate(dateService.getDate());
        }
    }

    @Override
    public void deleteProduct(int i) {
        for (Iterator<Product> iter = repository.listIterator(); iter.hasNext(); ) {
            Product p = iter.next();
            if (p.getId() == i) {
                iter.remove();
            }
        }
    }

    public void setCreateAddDate(boolean createAddDate) {
        this.createAddDate = createAddDate;
    }

    public void setCreateUpdateDate(boolean createUpdateDate) {
        this.createUpdateDate = createUpdateDate;
    }

    public void setCreateReadDate(boolean createReadDate) {
        this.createReadDate = createReadDate;
    }

    @Override
    public List<Product> getProductsByRegexp(String regexp) {
        return this.repository.stream()
                .filter(p -> p.getName().matches(regexp))
                .collect(Collectors.toList());
    }
}