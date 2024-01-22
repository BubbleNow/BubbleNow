package pl.bubblenow.controllers;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.bubblenow.repositories.*;

@Controller
@RequestMapping(path = "/entities")
@AllArgsConstructor
public class EntityController {

    private final SyrupRepository syrupRepository;
    private final AdditionRepository additionRepository;
    private final MilkRepository milkRepository;
    private final BubbleTeaRepository bubbleTeaRepository;
    private final KindRepository kindRepository;
    private final SizeRepository sizeRepository;
    private final OrderRepository orderRepository;

    @GetMapping(path = {"", "/"})
    public String index(Model model) {
        model.addAttribute("syrups", syrupRepository.findAll());
        model.addAttribute("additions", additionRepository.findAll());
        model.addAttribute("milks", milkRepository.findAll());
        model.addAttribute("bubbleTeas", bubbleTeaRepository.findAll());
        model.addAttribute("kinds", kindRepository.findAll());
        model.addAttribute("sizes", sizeRepository.findAll());
        model.addAttribute("orders", orderRepository.findAll());

        return "pages/admin/entities";
    }
}
