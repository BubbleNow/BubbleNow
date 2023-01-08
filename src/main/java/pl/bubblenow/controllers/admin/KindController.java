package pl.bubblenow.controllers.admin;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.bubblenow.models.Addition;
import pl.bubblenow.models.Kind;
import pl.bubblenow.repositories.KindRepository;

@Controller
@RequestMapping(path = "admin/kinds")
public class KindController {
    private final KindRepository kindRepository;

    public KindController(KindRepository kindRepository) {
        this.kindRepository = kindRepository;
    }

    @GetMapping(path = {"", "/"})
    public String kindIndex(Model model) {
        model.addAttribute("context", "kind");
        model.addAttribute("kinds", kindRepository.findAll());
        return "pages/admin/kinds/kind";
    }

    @GetMapping(path = {"create", "create/"})
    public String create(Model model) {
        model.addAttribute("context", "kind");
        model.addAttribute("kind", new Kind());

        return "pages/admin/kinds/create";
    }

    @PostMapping(path = "/create")
    public String store(@Valid @ModelAttribute("kind") Kind kind, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            this.kindRepository.save(kind);

            return "redirect:/admin/kinds";
        }

        return "pages/admin/kinds/create";
    }
}
