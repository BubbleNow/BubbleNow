package pl.bubblenow.controllers.admin;

import com.electronwill.nightconfig.core.conversion.Path;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
