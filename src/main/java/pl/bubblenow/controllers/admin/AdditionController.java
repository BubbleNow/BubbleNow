package pl.bubblenow.controllers.admin;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.bubblenow.models.Addition;
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

    @GetMapping(path = {"create", "create/"})
    public String create(Model model) {
        model.addAttribute("context", "addition");
        model.addAttribute("addition", new Addition());

        return "pages/admin/additions/create";
    }

    @PostMapping(path = "/create")
    public String store(@Valid @ModelAttribute("addition") Addition addition, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            this.additionRepository.save(addition);

            return "redirect:/admin/additions";
        }

        return "pages/admin/additions/create";
    }
    @GetMapping(path = "/{id}")
    public String edit(@PathVariable int id, Model model){

        model.addAttribute("addition", additionRepository.findById(id));
        return "pages/admin/additions/edit";
    }

    @PostMapping(path = "/{id}" )
    public String update(@ModelAttribute("addition") Addition addition, BindingResult bindingResult){
        if(!bindingResult.hasErrors()){
            additionRepository.save(addition);
            return "redirect:/admin/additions";
        }
        return "pages/admin/additions/edit";
    }

}
