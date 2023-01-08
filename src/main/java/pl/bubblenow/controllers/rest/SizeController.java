package pl.bubblenow.controllers.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.bubblenow.repositories.SizeRepository;

@RestController("SizeRestController")
@RequestMapping(path = "/sizes")
public class SizeController {

    SizeRepository sizeRepository;

    public SizeController(SizeRepository sizeRepository) {
        this.sizeRepository = sizeRepository;
    }

    @GetMapping(path = {"", "/"})
    public ResponseEntity<?> index() {
        return ResponseEntity.ok(sizeRepository.findAll());
    }

}
