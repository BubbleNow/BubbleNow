package pl.bubblenow.controllers.rest;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.bubblenow.models.*;
import pl.bubblenow.models.DTOs.BubbleTeaDTO;
import pl.bubblenow.repositories.*;
import pl.bubblenow.services.OrderService;


import java.math.BigDecimal;


@RestController("OrderRestController")
@RequestMapping(path = "/api/orders")
@AllArgsConstructor
public class OrderController {
    private final OrderRepository orderRepository;
    private final OrderService orderService;



    @GetMapping("/get-price")
    public BigDecimal getPrice(
            @RequestParam(required = false) Size size,
            @RequestParam(required = false) Milk milk,
            @RequestParam(required = false) Addition addition
    ) {
        return orderService.countPrice(addition, milk, size);
    }

    @PostMapping("/create")
    public int create(@RequestBody BubbleTeaDTO bubbleTeaRequest) {

        return orderService.create(bubbleTeaRequest);

    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllOrder() {
        return ResponseEntity.ok(orderRepository.findAll());
    }

}
