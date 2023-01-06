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
    private final SyrupRepository syrupRepository;
    private final AdditionRepository additionRepository;
    private final BaseRepository baseRepository;
    private final SizeRepository sizeRepository;
    private final KindRepository kindRepository;
    private final BubbleTeaRepository bubbleTeaRepository;

    public OrderController(OrderRepository orderRepository, OrderService orderService,
                           SyrupRepository syrupRepository,
                           AdditionRepository additionRepository,
                           BaseRepository baseRepository,
                           SizeRepository sizeRepository,
                           KindRepository kindRepository,
                           BubbleTeaRepository bubbleTeaRepository) {
        this.orderRepository = orderRepository;
        this.orderService = orderService;
        this.syrupRepository = syrupRepository;
        this.additionRepository = additionRepository;
        this.baseRepository = baseRepository;
        this.sizeRepository = sizeRepository;
        this.kindRepository = kindRepository;
        this.bubbleTeaRepository = bubbleTeaRepository;
    }

    @GetMapping("/get-price")
    public BigDecimal getPrice(
            @RequestParam Size size,
            @RequestParam Base base,
            @RequestParam(required = false) Addition addition
    ) {
        return orderService.countPrice(addition, base, size);
    }

    @PostMapping("/add-Bubble")
    public Order create(
            @RequestParam(required = false) Addition addition,
            @RequestParam Syrup syrup,
            @RequestParam Base base,
            @RequestParam Size size,
            @RequestParam Kind kind) {

        return orderService.create(addition, syrup, base, size, kind);
    }

}
