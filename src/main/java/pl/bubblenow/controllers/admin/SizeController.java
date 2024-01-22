package pl.bubblenow.controllers.admin;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.bubblenow.models.Size;
import pl.bubblenow.repositories.SizeRepository;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Controller
@RequestMapping(path = "admin/sizes")
@AllArgsConstructor
public class SizeController {
    private final SizeRepository sizeRepository;


    @GetMapping(path = {"", "/"})
    public String sizeIndex(Model model) {
        model.addAttribute("context", "size");
        model.addAttribute("sizes", sizeRepository.findAll());
        model.addAttribute("pageTitle", "Lista rozmiarów");
        model.addAttribute("createPath", "/admin/sizes/create");
        return "pages/admin/sizes/list";
    }

    @GetMapping(path = {"create", "create/"})
    public String create(Model model) {
        model.addAttribute("context", "size");
        model.addAttribute("size", new Size());
        model.addAttribute("pageTitle", "Dodaj rozmiar");

        return "pages/admin/sizes/form";
    }

    @PostMapping(path = "/create")
    public String store(@Valid @ModelAttribute("size") Size size,
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
                size.setFilePath(filePath.getFileName().toString());
            } catch (IOException e) {
                throw new IOException("Nie mozna bylo zapisac pliku:" + fileName);
            }
            this.sizeRepository.save(size);

            return "redirect:/admin/sizes";
        }
        model.addAttribute("context", "size");
        model.addAttribute("pageTitle", "Dodaj rozmiar");
        return "pages/admin/sizes/form";
    }

    @GetMapping(path = {"/{id}/edit/", "/{id}/edit"})
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("context", "size");
        model.addAttribute("size", sizeRepository.findById(id));
        model.addAttribute("pageTitle", "Edytuj rozmiar");

        return "pages/admin/sizes/form";
    }

    @PostMapping(path = {"/{id}/edit/", "/{id}/edit"})
    public String update(@Valid @ModelAttribute("size") Size size,
                         BindingResult bindingResult,
                         Model model) {
        if (!bindingResult.hasErrors()) {
            sizeRepository.save(size);
            return "redirect:/admin/sizes";
        }
        model.addAttribute("context", "size");
        model.addAttribute("pageTitle", "Edytuj rozmiar");
        return "pages/admin/sizes/form";
    }

    @PostMapping(path = {"/{id}/delete", "/{id}/delete/"})
    public String delete(@PathVariable int id) {
        sizeRepository.deleteById(id);
        return "redirect:/admin/sizes";
    }

}
