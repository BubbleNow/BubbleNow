package pl.bubblenow.controllers.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.bubblenow.repositories.MilkRepository;
import pl.bubblenow.services.MilkService;

@RestController("MilkRestController")
@RequestMapping(path = "/api/milks")
@AllArgsConstructor
public class MilkController {

    private final MilkRepository milkRepository;
    private final MilkService milkService;

    @GetMapping(path = {"", "/"})
    public ResponseEntity<?> index() {
        return ResponseEntity.ok(milkRepository.findAll());
    }

    @GetMapping(path = {"/search/", "/search"})
    public ResponseEntity<?> searchMilks(String query) {
        return ResponseEntity.ok(milkService.searchMilk(query));
    }

}
