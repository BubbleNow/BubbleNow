package pl.bubblenow.controllers.admin;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.bubblenow.models.Kind;
import pl.bubblenow.repositories.KindRepository;
import pl.bubblenow.services.KindService;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Controller
@RequestMapping(path = "admin/kinds")
@AllArgsConstructor
public class KindController {
    private final KindRepository kindRepository;
    private final KindService kindService;


    @GetMapping(path = {"", "/"})
    public String kindIndex(Model model) {
        model.addAttribute("context", "kind");
        model.addAttribute("kinds", kindRepository.findAll());
        model.addAttribute("createPath", "/admin/kinds/create");
        model.addAttribute("pageTitle", "Lista rodzajów herbat");
        return "pages/admin/kinds/list";
    }

    @GetMapping(path = {"create", "create/"})
    public String create(Model model) {
        model.addAttribute("context", "kind");
        model.addAttribute("kind", new Kind());
        model.addAttribute("pageTitle", "Dodaj nowy rodzaj herbaty");

        return "pages/admin/kinds/form";
    }

    @PostMapping(path = "/create")
    public String store(@Valid @ModelAttribute("kind") Kind kind,
                        BindingResult bindingResult,
                        Model model,
                        @RequestParam("image") MultipartFile image) throws IOException {
        if (!bindingResult.hasErrors()) {
            kindService.uploadImage(kind, image);
            kindRepository.save(kind);

            return "redirect:/admin/kinds";
        }
        model.addAttribute("context", "kind");
        model.addAttribute("pageTitle", "Dodaj nowy rodzaj herbaty");
        return "pages/admin/kinds/form";
    }

    @GetMapping(path = {"/{id}/edit/", "/{id}/edit"})
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("context", "kind");
        model.addAttribute("kind", kindRepository.findById(id));
        model.addAttribute("pageTitle", "Edytuj rodzaj herbaty");

        return "pages/admin/kinds/form";
    }

    @PostMapping(path = {"/{id}/edit/", "/{id}/edit"})
    public String update(@Valid @ModelAttribute("kind") Kind kind,
                         BindingResult bindingResult,
                         Model model) {
        if (!bindingResult.hasErrors()) {
            kindRepository.save(kind);
            return "redirect:/admin/kinds";
        }
        model.addAttribute("context", "kind");
        model.addAttribute("pageTitle", "Edytuj rodzaj herbaty");
        return "pages/admin/kinds/form";
    }

    @PostMapping(path = {"/{id}/delete", "/{id}/delete/"})
    public String delete(@PathVariable int id) {
        kindRepository.deleteById(id);
        return "redirect:/admin/kinds";
    }

}
