package pl.bubblenow.controllers.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.bubblenow.repositories.SyrupRepository;

@RestController
@RequestMapping(path = "/syrups")
public class SyrupController {

    SyrupRepository syrupRepository;

    public SyrupController(SyrupRepository syrupRepository) {
        this.syrupRepository = syrupRepository;
    }

    @GetMapping(path = {"", "/"})
    public ResponseEntity<?> index() {
        return ResponseEntity.ok(syrupRepository.findAll());
    }

}
