package pl.bubblenow.controllers.rest;


import org.springframework.web.bind.annotation.*;
import pl.bubblenow.models.*;
import pl.bubblenow.repositories.*;
import pl.bubblenow.services.OrderService;


import java.math.BigDecimal;


@RestController("OrderRestController")
@RequestMapping(path = "/api/orders")
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
            @RequestParam(required = false) Size size,
            @RequestParam(required = false) Milk milk,
            @RequestParam(required = false) Addition addition
    ) {
        return orderService.countPrice(addition, milk, size);
    }

    @PostMapping("/create")
    public int create(
            @RequestParam(required = false) Addition addition,
            @RequestParam Syrup syrup,
            @RequestParam Milk milk,
            @RequestParam Size size,
            @RequestParam Kind kind
    ) {
        return orderService.create(addition, syrup, milk, size, kind);
    }

}
