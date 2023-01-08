package pl.bubblenow.controllers.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.bubblenow.repositories.KindRepository;

@RestController("KindRestController")
@RequestMapping(path = "/kinds")
public class KindController {

    KindRepository kindRepository;

    public KindController(KindRepository kindRepository) {
        this.kindRepository = kindRepository;
    }

    @GetMapping(path = {"", "/"})
    public ResponseEntity<?> index() {
        return ResponseEntity.ok(kindRepository.findAll());
    }

}
