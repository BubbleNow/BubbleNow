package pl.bubblenow.controllers.admin;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.bubblenow.models.Milk;
import pl.bubblenow.models.Order;
import pl.bubblenow.repositories.MilkRepository;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

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
                        Model model,
                        @RequestParam("image") MultipartFile image) throws IOException {

        if (!bindingResult.hasErrors()) {
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(image.getOriginalFilename()));
            String uploadDir = "src\\main\\resources\\static\\uploads\\";
            // src\\main\\resources\\static\\uploads\\
            //  TODO: 57 LINIJKA POPRAW
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            try (InputStream inputStream = image.getInputStream()) {
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                milk.setFile_path(filePath.getFileName().toString());
            } catch (IOException e) {
                throw new IOException("Nie mozna bylo zapisac pliku:" + fileName);
            }
            milkRepository.save(milk);

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
