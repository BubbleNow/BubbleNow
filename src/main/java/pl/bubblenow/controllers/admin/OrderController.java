package pl.bubblenow.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.bubblenow.repositories.OrderRepository;

@Controller
@RequestMapping(path = "admin/orders")
public class OrderController {
    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping(path = {"", "/"})
    public String orderIndex(Model model) {
        model.addAttribute("pageTitle", "Lista zamówień");
        model.addAttribute("context", "order");
        model.addAttribute("orders", orderRepository.findAll());
        return "pages/admin/orders/list";
    }
}
