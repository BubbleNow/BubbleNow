package pl.bubblenow.controllers.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.bubblenow.repositories.KindRepository;
import pl.bubblenow.services.KindService;

@RestController("KindRestController")
@RequestMapping(path = "/api/kinds")
public class KindController {

    KindRepository kindRepository;
    KindService kindService;

    public KindController(KindRepository kindRepository,
                          KindService kindService) {
        this.kindRepository = kindRepository;
        this.kindService = kindService;
    }

    @GetMapping(path = {"", "/"})
    public ResponseEntity<?> index() {
        return ResponseEntity.ok(kindRepository.findAll());
    }

    @GetMapping(path = {"/search/", "/search"})
    public ResponseEntity<?> searchKinds(String query) {
        return ResponseEntity.ok(kindService.searchKind(query));
    }


}
