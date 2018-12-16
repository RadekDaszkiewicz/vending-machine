package pl.edu.pjatk.tau.labone.service;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pjatk.tau.labone.domain.Order;
import pl.edu.pjatk.tau.labone.domain.Product;

import java.util.Collection;
import java.util.List;

@Component
@Transactional
public class OrderManagerImpl implements OrderManager {

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {return sessionFactory;}

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addProduct(Product p) {
        sessionFactory.getCurrentSession().persist(p);
        sessionFactory.getCurrentSession().flush();
    }

    public Collection<Product> getAllProducts() {
        return sessionFactory.getCurrentSession().createQuery("SELECT p from Product p").list();
    }

    public void deleteProduct(Product p) {
        sessionFactory.getCurrentSession().delete(p);
    }

    public Product getProductById(Integer id) {
        Query q = sessionFactory.getCurrentSession().createQuery("select p from Product p where p.id = :id");
        q.setParameter("id", id);
        return (Product) q.uniqueResult();
    }

    public void updateProduct(Product p) {
        sessionFactory.getCurrentSession().update(p);
    }

    public Integer addNewOrder(Order o) {
        return (Integer) sessionFactory.getCurrentSession().save(o);
    }

    public void addProductToOrder(Product p, Order o) {
        o.getProducts().add(p);
        sessionFactory.getCurrentSession().update(o);
    }

    public void removeProductFromOrder(Product p, Order o) {
//        o.getProducts().iterator().next() //todo
    }

    public Order getOrderById(Integer id) {
        Query q = sessionFactory.getCurrentSession().createQuery("select o from \"Order\" o where o.id = :id");
        q.setParameter("id", id);
        return (Order) q.uniqueResult();
    }

    public List<Order> getAllOrders() {
        return sessionFactory.getCurrentSession().createQuery("select o from \"Order\" o").list();
    }
}
