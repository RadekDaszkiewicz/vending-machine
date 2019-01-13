package pl.edu.pjatk.tau.labone.domain;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author s14646
 */
@Entity
public class Product {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private BigDecimal price;

    public Product() {
    }

    public Product(Integer id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (!(o instanceof Product)) {
            return false;
        }

        Product product = (Product) o;

        if (this.id != product.id) {
            return false;
        }
        if (this.name != null ? !this.name.equals(product.name) : product.name != null) {
            return false;
        }
        return this.price != null ? this.price.equals(product.price) : product.price == null;

    }

    @Override
    public int hashCode() {
        int result = this.id;
        result = 31 * result + (this.name != null ? this.name.hashCode() : 0);
        result = 31 * result + (this.price != null ? this.price.hashCode() : 0);
        return result;
    }
}