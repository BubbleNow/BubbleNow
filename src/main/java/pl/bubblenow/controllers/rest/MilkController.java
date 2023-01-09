package pl.bubblenow.controllers.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.bubblenow.repositories.MilkRepository;

@RestController("MilkRestController")
@RequestMapping(path = "/milks")
public class MilkController {

    MilkRepository milkRepository;

    public MilkController(MilkRepository milkRepository) {
        this.milkRepository = milkRepository;
    }

    @GetMapping(path = {"", "/"})
    public ResponseEntity<?> index() {
        return ResponseEntity.ok(milkRepository.findAll());
    }

}
