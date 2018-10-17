package pl.edu.pjatk.tau.labone.domain;

import java.math.BigDecimal;

/**
 * @author s14646
 */
public class Product {

	private int id;
	private String name;
	private BigDecimal price;

	public Product() {
	}

	public Product(int id, String name, BigDecimal price) {
		this.id = id;
		this.name = name;
		this.price = price;
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