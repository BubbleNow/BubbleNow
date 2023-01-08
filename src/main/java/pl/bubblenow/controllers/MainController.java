package pl.bubblenow.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = {"","/"})
public class MainController {
    @GetMapping
    public String index(){
        return "pages/main/index";
    }

    @GetMapping(path = "/admin")
    public String adminIndex(Model model){
        model.addAttribute("context","home");
        return "pages/admin/index";
    }
}
