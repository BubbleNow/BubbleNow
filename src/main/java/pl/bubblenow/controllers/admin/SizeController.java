package pl.bubblenow.controllers.admin;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.bubblenow.models.Size;
import pl.bubblenow.repositories.SizeRepository;

@Controller
@RequestMapping(path = "admin/sizes")
public class SizeController {
    private final SizeRepository sizeRepository;

    public SizeController(SizeRepository sizeRepository) {
        this.sizeRepository = sizeRepository;
    }

    @GetMapping(path = {"", "/"})
    public String sizeIndex(Model model) {
        model.addAttribute("context", "size");
        model.addAttribute("sizes", sizeRepository.findAll());
        return "pages/admin/sizes/size";
    }

    @GetMapping(path = {"create", "create/"})
    public String create(Model model) {
        model.addAttribute("context", "size");
        model.addAttribute("size", new Size());

        return "pages/admin/sizes/create";
    }

    @PostMapping(path = "/create")
    public String store(@Valid @ModelAttribute("size") Size size, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            this.sizeRepository.save(size);

            return "redirect:/admin/sizes";
        }

        return "pages/admin/sizes/create";
    }

    @GetMapping(path = {"/{id}/edit/","/{id}/edit"})
    public String edit(@PathVariable int id, Model model) {

        model.addAttribute("size", sizeRepository.findById(id));
        return "pages/admin/sizes/edit";
    }

    @PostMapping(path = {"/{id}/edit/","/{id}/edit"})
    public String update(@ModelAttribute("size") Size size, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            sizeRepository.save(size);
            return "redirect:/admin/sizes";
        }
        return "pages/admin/sizes/edit";
    }

    @PostMapping(path = {"/{id}/delete", "/{id}/delete/"})
    public String delete(@PathVariable int id) {
        sizeRepository.deleteById(id);
        return "redirect:/admin/sizes";
    }

}
