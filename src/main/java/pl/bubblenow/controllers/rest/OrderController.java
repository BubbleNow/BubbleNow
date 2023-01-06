package pl.bubblenow.controllers.rest;


import org.aspectj.weaver.ast.Or;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.bubblenow.models.*;
import pl.bubblenow.repositories.*;
import pl.bubblenow.services.OrderService;


import java.math.BigDecimal;
import java.util.Date;

@RestController
@RequestMapping(path = "/orders")
public class OrderController {
    OrderRepository orderRepository;
    OrderService orderService;

    public OrderController(
            OrderRepository orderRepository,
            OrderService orderService
    ) {
        this.orderRepository = orderRepository;
        this.orderService = orderService;
    }

    @GetMapping("/get-price")
    public BigDecimal getPrice(
            @RequestParam Size size,
            @RequestParam Base base,
            @RequestParam(required = false) Addition addition
    ) {
        return orderService.countPrice(addition, base, size);
    }

    @PostMapping("/create")
    public Order create(
            @RequestParam(required = false) Addition addition,
            @RequestParam Syrup syrup,
            @RequestParam Base base,
            @RequestParam Size size,
            @RequestParam Kind kind
    ) {
        return orderService.create(addition, syrup, base, size, kind);
    }

}
