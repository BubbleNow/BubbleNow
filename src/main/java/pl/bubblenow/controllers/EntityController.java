package pl.bubblenow.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.bubblenow.repositories.*;

@Controller
@RequestMapping(path = "/entities")
public class EntityController {

    private final SyrupRepository syrupRepository;
    private final AdditionRepository additionRepository;
    private final BaseRepository baseRepository;
    private final BubbleTeaRepository bubbleTeaRepository;
    private final KindRepository kindRepository;
    private final SizeRepository sizeRepository;
    private final OrderRepository orderRepository;

    public EntityController(SyrupRepository syrupRepository,
                            AdditionRepository additionRepository,
                            BaseRepository baseRepository,
                            BubbleTeaRepository bubbleTeaRepository,
                            KindRepository kindRepository,
                            SizeRepository sizeRepository,
                            OrderRepository orderRepository) {
        this.syrupRepository = syrupRepository;
        this.additionRepository = additionRepository;
        this.baseRepository = baseRepository;
        this.bubbleTeaRepository = bubbleTeaRepository;
        this.kindRepository = kindRepository;
        this.sizeRepository = sizeRepository;
        this.orderRepository = orderRepository;
    }


    @GetMapping(path = {"", "/"})
    public String index(Model model) {
        model.addAttribute("syrups", syrupRepository.findAll());
        model.addAttribute("additions", additionRepository.findAll());
        model.addAttribute("bases", baseRepository.findAll());
        model.addAttribute("bubbleTeas", bubbleTeaRepository.findAll());
        model.addAttribute("kinds", kindRepository.findAll());
        model.addAttribute("sizes", sizeRepository.findAll());
        model.addAttribute("orders", orderRepository.findAll());

        return "entities";
    }
}
