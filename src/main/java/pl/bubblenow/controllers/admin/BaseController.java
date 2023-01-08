package pl.bubblenow.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
