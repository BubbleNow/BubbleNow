package pl.bubblenow.controllers.admin;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.bubblenow.models.Syrup;
import pl.bubblenow.repositories.SyrupRepository;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

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
        model.addAttribute("pageTitle", "Lista syropów");
        model.addAttribute("createPath", "/admin/syrups/create");
        return "pages/admin/syrups/list";
    }

    @GetMapping(path = {"create", "create/"})
    public String create(Model model) {
        model.addAttribute("pageTitle", "Dodaj syrop");
        model.addAttribute("context", "syrup");
        model.addAttribute("syrup", new Syrup());

        return "pages/admin/syrups/form";
    }

    @PostMapping(path = "/create")
    public String store(@Valid @ModelAttribute("syrup") Syrup syrup,
                        BindingResult bindingResult,
                        Model model,
                        @RequestParam("image") MultipartFile image) throws IOException {
        System.out.println("jestem w 53");
        System.out.println(bindingResult.getAllErrors());
        if (!bindingResult.hasErrors()) {
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(image.getOriginalFilename()));
            String uploadDir = "src\\main\\resources\\static\\uploads\\";
            // src\\main\\resources\\static\\uploads\\
            //  TODO: 57 LINIJKA POPRAW
            System.out.println("jestem w 59");
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                System.out.println("jestem w 61");
                Files.createDirectories(uploadPath);
            }
            System.out.println("jestem w 64");
            try (InputStream inputStream = image.getInputStream()) {
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                syrup.setFile_path(filePath.getFileName().toString());
            } catch (IOException e) {
                System.out.println("jestem w 70");
                throw new IOException("Nie mozna bylo zapisac pliku:" + fileName);
            }
            System.out.println("jestem w 73");
            syrupRepository.save(syrup);

            return "redirect:/admin/syrups";
        }
        System.out.println("jestem w 78");
        model.addAttribute("context", "syrup");
        model.addAttribute("pageTitle", "Dodaj syrop");
        return "pages/admin/syrups/form";
    }

    @GetMapping(path = {"/{id}/edit/", "/{id}/edit"})
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("pageTitle", "Edytuj syrop");
        model.addAttribute("syrup", syrupRepository.findById(id));
        model.addAttribute("context", "syrup");
        return "pages/admin/syrups/form";
    }

    @PostMapping(path = {"/{id}/edit/", "/{id}/edit"})
    public String update(@Valid @ModelAttribute("syrup") Syrup syrup,
                         BindingResult bindingResult,
                         Model model) {
        if (!bindingResult.hasErrors()) {
            syrupRepository.save(syrup);
            return "redirect:/admin/syrups";
        }
        model.addAttribute("context", "syrup");
        model.addAttribute("pageTitle", "Edytuj syrop");
        return "pages/admin/syrups/form";
    }

    @PostMapping(path = {"/{id}/delete", "/{id}/delete/"})
    public String delete(@PathVariable int id) {
        syrupRepository.deleteById(id);
        return "redirect:/admin/syrups";
    }

}
