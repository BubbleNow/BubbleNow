package pl.bubblenow.controllers.rest;


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
            @RequestParam int sizeId,
            @RequestParam int baseId,
            @RequestParam(required = false) Integer additionId
    ) {
        return orderService.countPrice(additionId, baseId, sizeId);
    }

    @PostMapping("/add-Bubble")
    public void createNew(
            @RequestParam int additionId,
            @RequestParam int syrupId,
            @RequestParam int baseId,
            @RequestParam int sizeId,
            @RequestParam int kindId) {

        createNew(additionId, syrupId, baseId, sizeId, kindId);
    }

    @GetMapping("/get-latest")
    public ResponseEntity<?> getLatest(){
        return ResponseEntity.ok(orderRepository.findTopByOrderByIdDesc());
    }
}
