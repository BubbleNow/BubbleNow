package pl.bubblenow.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.bubblenow.repositories.MilkRepository;

@Controller
@RequestMapping(path = {"", "/"})
public class MainController {

    @GetMapping
    public String index() {
        return "pages/main/index";
    }

    @GetMapping(path ={ "/admin", "/admin/"})
    public String adminIndex(Model model) {
        model.addAttribute("context", "home");
        return "pages/admin/index";
    }


}
