package pl.bubblenow.controllers.admin;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.bubblenow.models.Milk;
import pl.bubblenow.repositories.MilkRepository;

@Controller
@RequestMapping(path = "admin/milks")
public class MilkController {

    private final MilkRepository milkRepository;

    public MilkController(MilkRepository milkRepository) {
        this.milkRepository = milkRepository;
    }

    @GetMapping(path = {"", "/"})
    public String milksIndex(Model model) {
        model.addAttribute("context", "milk");
        model.addAttribute("milks", milkRepository.findAll());
        model.addAttribute("createPath", "/admin/milks/create");
        model.addAttribute("pageTitle", "Lista mlek");
        return "pages/admin/milks/list";
    }

    @GetMapping(path = {"create/", "create"})
    public String create(Model model) {
        model.addAttribute("context", "milk");
        model.addAttribute("milk", new Milk());
        model.addAttribute("pageTitle", "Dodaj mleko");

        return "pages/admin/milks/form";
    }

    @PostMapping(path = "/create")
    public String store(@Valid @ModelAttribute("milk") Milk milk,
                        BindingResult bindingResult,
                        Model model) {
        if (!bindingResult.hasErrors()) {
            this.milkRepository.save(milk);

            return "redirect:/admin/milks";
        }
        model.addAttribute("context", "milk");
        model.addAttribute("pageTitle", "Dodaj mleko");
        return "pages/admin/milks/form";
    }

    @GetMapping(path = {"/{id}/edit/", "/{id}/edit"})
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("milk", milkRepository.findById(id));
        model.addAttribute("pageTitle", "Edytuj mleko");
        model.addAttribute("context", "milk");

        return "pages/admin/milks/form";
    }

    @PostMapping(path = {"/{id}/edit/", "/{id}/edit"})
    public String update(@Valid @ModelAttribute("milk") Milk milk,
                         BindingResult bindingResult,
                         Model model) {
        if (!bindingResult.hasErrors()) {
            milkRepository.save(milk);
            return "redirect:/admin/milks";
        }
        model.addAttribute("context", "milk");
        model.addAttribute("pageTitle", "Edytuj mleko");
        return "pages/admin/milks/form";
    }

    @PostMapping(path = {"/{id}/delete", "/{id}/delete/"})
    public String delete(@PathVariable int id) {
        milkRepository.deleteById(id);
        return "redirect:/admin/milks";
    }


}
