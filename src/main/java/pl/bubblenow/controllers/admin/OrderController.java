package pl.bubblenow.controllers.admin;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.bubblenow.repositories.OrderRepository;
import pl.bubblenow.services.OrderService;

@Controller
@RequestMapping(path = "admin/orders")
public class OrderController {
    private final OrderRepository orderRepository;
    private final OrderService orderService;

    public OrderController(OrderRepository orderRepository, OrderService orderService) {
        this.orderRepository = orderRepository;
        this.orderService = orderService;
    }

    @GetMapping(path = {"", "/"})
    public String orderIndex(Model model) {
        model.addAttribute("pageTitle", "Lista zamówień");
        model.addAttribute("context", "order");
        model.addAttribute("orders", orderRepository.findAll());

        return "pages/admin/orders/list";
    }

    @PostMapping(path = {"/{id}/update-status", "/{id}/update-status/"})
    public String updateStatus(@PathVariable int id) {
        orderService.updateToNextStatus(id);

        return "redirect:/admin/orders";
    }

}
