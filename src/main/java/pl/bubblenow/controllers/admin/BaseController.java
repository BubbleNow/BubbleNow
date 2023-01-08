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
import pl.bubblenow.models.Base;
import pl.bubblenow.repositories.BaseRepository;

@Controller
@RequestMapping(path = "admin/bases")
public class BaseController {

    private final BaseRepository baseRepository;

    public BaseController(BaseRepository baseRepository) {
        this.baseRepository = baseRepository;
    }

    @GetMapping(path = {"", "/"})
    public String basesIndex(Model model) {
        model.addAttribute("context", "base");
        model.addAttribute("bases", baseRepository.findAll());
        return "pages/admin/bases/base";
    }

    @GetMapping(path = {"create/", "create"})
    public String create(Model model){
        model.addAttribute("context", "base");
        model.addAttribute("base", new Base());

        return "pages/admin/bases/create";
    }

    @PostMapping(path = "/create")
    public String store(@Valid @ModelAttribute("base") Base base, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            this.baseRepository.save(base);

            return "redirect:/admin/bases";
        }

        return "pages/admin/bases/create";
    }
}
