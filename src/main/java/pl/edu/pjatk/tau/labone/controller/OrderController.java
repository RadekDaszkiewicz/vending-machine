package pl.edu.pjatk.tau.labone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjatk.tau.labone.domain.Product;
import pl.edu.pjatk.tau.labone.service.OrderService;

import java.sql.SQLException;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping("/")
    public String index() {
        return "This is non rest, just checking if everything works.";
    }

    @RequestMapping(value = "/products/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Product getProduct(@PathVariable("id") Integer id) throws SQLException {
        return this.orderService.getProductById(id);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.POST,
                    consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Product addProduct(@RequestBody Product p) {
        if (this.orderService.createProduct(p) < 1) {
            return null;
        }
        return p;
    }

}
