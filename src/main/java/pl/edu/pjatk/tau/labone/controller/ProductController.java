package pl.edu.pjatk.tau.labone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjatk.tau.labone.domain.Product;
import pl.edu.pjatk.tau.labone.service.ProductService;

import java.sql.SQLException;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @RequestMapping("/")
    public String index() {
        return "This is non rest, just checking if everything works.";
    }

    @RequestMapping(value = "/products",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Product addProduct(@RequestBody Product p) {
        if (this.productService.addProduct(p) < 1) return null;
        return p;
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Integer deleteProduct(@PathVariable("id") Integer id) throws SQLException {
        return this.productService.deleteProduct(this.productService.getProduct(id));
    }

    @RequestMapping(value = "/products/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Product getProduct(@PathVariable("id") Integer id) throws SQLException {
        return this.productService.getProduct(id);
    }

    @RequestMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Product> getAllProducts() {
        return this.productService.getAll();
    }


}
