package pl.bubblenow.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.bubblenow.repositories.SyrupRepository;

@Controller
@RequestMapping(path = "admin/syrups")
public class SyrupController {
    private final SyrupRepository syrupRepository;

    public SyrupController(SyrupRepository syrupRepository) {
        this.syrupRepository = syrupRepository;
    }

    @GetMapping(path = {"", "/"})
    public String syrupIndex(Model model) {
        model.addAttribute("context", "syrup");
        model.addAttribute("syrups", syrupRepository.findAll());
        return "pages/admin/syrups/syrup";
    }
}
