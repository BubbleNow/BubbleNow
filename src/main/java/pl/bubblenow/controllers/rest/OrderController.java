package pl.bubblenow.controllers.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.bubblenow.repositories.OrderRepository;
import pl.bubblenow.services.OrderService;

@RestController
@RequestMapping(path = "/orders")
public class OrderController {
    OrderRepository orderRepository;
    OrderService orderService;

    public OrderController(OrderRepository orderRepository, OrderService orderService) {
        this.orderRepository = orderRepository;
        this.orderService = orderService;
    }

    @GetMapping("/get-price")
    public double getPrice(
            @RequestParam int size_id,
            @RequestParam int base_id,
            @RequestParam(required = false) Integer addition_id
    ) {
        return orderService.countPrice(addition_id, base_id, size_id);
    }

}
