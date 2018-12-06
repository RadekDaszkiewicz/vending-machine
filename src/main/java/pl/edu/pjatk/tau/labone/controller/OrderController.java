package pl.edu.pjatk.tau.labone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjatk.tau.labone.service.OrderService;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping("/")
    public String index() {
        return "This is non rest, just checking if everything works.";
    }
}
