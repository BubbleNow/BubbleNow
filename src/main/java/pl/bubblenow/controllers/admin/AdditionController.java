package pl.bubblenow.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.bubblenow.repositories.AdditionRepository;

@Controller
@RequestMapping(path = "admin/additions")
public class AdditionController {
    private final AdditionRepository additionRepository;

    public AdditionController(AdditionRepository additionRepository) {
        this.additionRepository = additionRepository;
    }

    @GetMapping(path = {"", "/"})
    public String additionIndex(Model model) {
        model.addAttribute("context", "addition");
        model.addAttribute("additions", additionRepository.findAll());
        return "pages/admin/additions/addition";
    }
}
