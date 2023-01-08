package pl.bubblenow.controllers.admin;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping(path= {"create", "create/"})
    public String create(Model model){
        model.addAttribute("context", "size");
        model.addAttribute("size", new Size());

        return "pages/admin/sizes/create";
    }

    @PostMapping(path = "/create")
    public String store(@Valid @ModelAttribute("size")Size size, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            this.sizeRepository.save(size);

            return "redirect:/admin/sizes";
        }

        return "pages/admin/sizes/create";
    }
}
