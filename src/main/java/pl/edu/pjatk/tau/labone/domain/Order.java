package pl.edu.pjatk.tau.labone.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @OneToMany(orphanRemoval = true)
    @JoinColumn(name="order_id")
    private List<Product> products;
    private BigDecimal value;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
