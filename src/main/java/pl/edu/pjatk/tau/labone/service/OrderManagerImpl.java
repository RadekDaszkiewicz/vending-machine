package pl.edu.pjatk.tau.labone.service;

import java.util.Collection;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pjatk.tau.labone.domain.Cart;
import pl.edu.pjatk.tau.labone.domain.Product;

@Component
@Transactional
public class OrderManagerImpl implements OrderManager {

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {return this.sessionFactory;}

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public int createProduct(Product p) {
        Integer id = (Integer) this.sessionFactory.getCurrentSession().save(p);
        this.sessionFactory.getCurrentSession().flush();
        return id;
    }

    public Collection<Product> getAllProducts() {
        return this.sessionFactory.getCurrentSession().createQuery("SELECT p from Product p").list();
    }

    public void deleteProduct(Product p) {
        this.sessionFactory.getCurrentSession().delete(p);
        this.sessionFactory.getCurrentSession().flush();
    }

    public Product getProductById(Integer id) {
        Query q = this.sessionFactory.getCurrentSession().createQuery("select p from Product p where p.id = :id");
        q.setParameter("id", id);
        return (Product) q.uniqueResult();
    }

    public void updateProduct(Product p) {
        this.sessionFactory.getCurrentSession().update(p);
        this.sessionFactory.getCurrentSession().flush();
    }

    public Integer addNewOrder(Cart o) {
        Integer id = (Integer) this.sessionFactory.getCurrentSession().save(o);
        this.sessionFactory.getCurrentSession().flush();
        return id;
    }

    public void addProductToOrder(Product p, Cart o) {
        o.getProducts().add(p);
        this.sessionFactory.getCurrentSession().update(o);
        this.sessionFactory.getCurrentSession().flush();
    }

    public void removeProductFromOrder(Product p, Cart o) {
        o.getProducts().removeIf(p1 -> p1.getId() == p.getId());
        this.sessionFactory.getCurrentSession().update(o);
        this.sessionFactory.getCurrentSession().flush();
    }

    public Cart getOrderById(Integer id) {
        Query q = this.sessionFactory.getCurrentSession().createQuery("select c from Cart c where c.id = :id");
        q.setParameter("id", id);
        return (Cart) q.uniqueResult();
    }

    public List<Cart> getAllOrders() {
        return this.sessionFactory.getCurrentSession().createQuery("select c from Cart c").list();
    }

    @Override
    public void deleteOrder(Cart c) {
        this.sessionFactory.getCurrentSession().delete(c);
    }

    @Override
    public Collection<Product> findProductsByName(String name) {
        Query q = this.sessionFactory.getCurrentSession().createQuery("select p from Product p where lower(p.name) like :name");
        q.setParameter("name", "%" + name.toLowerCase() + "%");
        return q.list();
    }
}
