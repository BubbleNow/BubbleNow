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
        model.addAttribute("pageTitle", "Lista dodatków");
        return "pages/admin/additions/list";
    }

    @GetMapping(path = {"create", "create/"})
    public String create(Model model) {
        model.addAttribute("context", "addition");
        model.addAttribute("addition", new Addition());
        model.addAttribute("pageTitle", "Dodaj nowy dodatek");
        model.addAttribute("formPath", "/admin/additions/create");

        return "pages/admin/additions/form";
    }

    @PostMapping(path = "/create")
    public String store(@Valid @ModelAttribute("addition") Addition addition, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            this.additionRepository.save(addition);

            return "redirect:/admin/additions";
        }

        return "pages/admin/additions/form";
    }

    @GetMapping(path = {"/{id}/edit/", "/{id}/edit"})
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("context", "addition");
        model.addAttribute("addition", additionRepository.findById(id));
        model.addAttribute("pageTitle", "Edytuj dodatek");
        model.addAttribute("formPath", "/admin/additions/" + id + "/edit");
        return "pages/admin/additions/form";
    }

    @PostMapping(path = {"/{id}/edit/", "/{id}/edit"})
    public String update(@ModelAttribute("addition") Addition addition, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            additionRepository.save(addition);
            return "redirect:/admin/additions";
        }
        return "pages/admin/additions/form";
    }

    @PostMapping(path = {"/{id}/delete", "/{id}/delete/"})
    public String delete(@PathVariable int id) {
        additionRepository.deleteById(id);
        return "redirect:/admin/additions";
    }

}
