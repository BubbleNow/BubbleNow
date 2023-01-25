package pl.bubblenow.controllers.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.bubblenow.repositories.MilkRepository;
import pl.bubblenow.services.MilkService;

@RestController("MilkRestController")
@RequestMapping(path = "/api/milks")
public class MilkController {

    MilkRepository milkRepository;
    MilkService milkService;

    public MilkController(MilkRepository milkRepository, MilkService milkService) {
        this.milkRepository = milkRepository;
        this.milkService = milkService;
    }

    @GetMapping(path = {"", "/"})
    public ResponseEntity<?> index() {
        return ResponseEntity.ok(milkRepository.findAll());
    }

    @GetMapping(path = {"/search/", "/search"})
    public ResponseEntity<?> searchMilks(String query) {
        return ResponseEntity.ok(milkService.searchMilk(query));
    }

}
