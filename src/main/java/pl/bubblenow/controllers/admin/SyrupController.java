package pl.bubblenow.controllers.admin;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.bubblenow.models.Syrup;
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

    @GetMapping(path = {"create", "create/"})
    public String create(Model model) {
        model.addAttribute("context", "syrup");
        model.addAttribute("syrup", new Syrup());

        return "pages/admin/syrups/create";
    }

    @PostMapping(path = "/create")
    public String store(@Valid @ModelAttribute("syrup") Syrup syrup, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            this.syrupRepository.save(syrup);

            return "redirect:/admin/syrups";
        }

        return "pages/admin/syrups/create";
    }

    @GetMapping(path = {"/{id}/edit/","/{id}/edit"})
    public String edit(@PathVariable int id, Model model) {

        model.addAttribute("syrup", syrupRepository.findById(id));
        return "pages/admin/syrups/edit";
    }

    @PostMapping(path = {"/{id}/edit/","/{id}/edit"})
    public String update(@ModelAttribute("syrup") Syrup syrup, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            syrupRepository.save(syrup);
            return "redirect:/admin/syrups";
        }
        return "pages/admin/syrups/edit";
    }
    @PostMapping(path = {"/{id}/delete", "/{id}/delete/"})
    public String delete(@PathVariable int id){
        syrupRepository.deleteById(id);
        return "redirect:/admin/syrups";
    }

}
