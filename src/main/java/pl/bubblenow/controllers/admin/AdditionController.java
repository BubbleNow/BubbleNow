package pl.bubblenow.controllers.admin;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.bubblenow.models.Addition;
import pl.bubblenow.repositories.AdditionRepository;
import pl.bubblenow.services.AdditionService;
import pl.bubblenow.services.ImageService;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Controller
@RequestMapping(path = "admin/additions")
@AllArgsConstructor
public class AdditionController {
    private final AdditionRepository additionRepository;
    private final AdditionService additionService;
    private final ImageService imageService;


    @GetMapping(path = {"", "/"})
    public String additionIndex(Model model) {
        model.addAttribute("context", "addition");
        model.addAttribute("additions", additionRepository.findAll());
        model.addAttribute("pageTitle", "Lista dodatków");
        model.addAttribute("createPath", "/admin/additions/create");
        return "pages/admin/additions/list";
    }

    @GetMapping(path = {"create", "create/"})
    public String create(Model model) {
        model.addAttribute("context", "addition");
        model.addAttribute("addition", new Addition());
        model.addAttribute("pageTitle", "Dodaj nowy dodatek");

        return "pages/admin/additions/form";
    }

    @PostMapping(path = "/create")
    public String store(@Valid @ModelAttribute("addition") Addition addition,
                        BindingResult bindingResult,
                        Model model,
                        @RequestParam("image") MultipartFile image) throws IOException {
        System.out.println(bindingResult.getAllErrors());
        if (!bindingResult.hasErrors()) {

            additionRepository.save(addition);
            addition.setFilePath(imageService.uploadImage(image));
            additionRepository.save(addition);
            return "redirect:/admin/additions";
        }
        model.addAttribute("pageTitle", "Dodaj nowy dodatek");
        model.addAttribute("context", "addition");
        return "pages/admin/additions/form";
    }


    @GetMapping(path = {"/{id}/edit/", "/{id}/edit"})
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("context", "addition");
        model.addAttribute("addition", additionRepository.findById(id));
        model.addAttribute("pageTitle", "Edytuj dodatek");
        return "pages/admin/additions/form";
    }

    @PostMapping(path = {"/{id}/edit/", "/{id}/edit"})
    public String update(@Valid @ModelAttribute("addition") Addition addition,
                         BindingResult bindingResult,
                         Model model) {
        if (!bindingResult.hasErrors()) {
            additionRepository.save(addition);
            return "redirect:/admin/additions";
        }
        model.addAttribute("pageTitle", "Edytuj dodatek");
        model.addAttribute("context", "addition");
        return "pages/admin/additions/form";
    }

    @PostMapping(path = {"/{id}/delete", "/{id}/delete/"})
    public String delete(@PathVariable int id) {
        additionRepository.deleteById(id);
        return "redirect:/admin/additions";
    }

}
