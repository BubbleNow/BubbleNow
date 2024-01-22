package pl.bubblenow.controllers.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.bubblenow.repositories.AdditionRepository;
import pl.bubblenow.services.AdditionService;

@RestController("AdditionRestController")
@RequestMapping(path = "/api/additions")
@AllArgsConstructor
public class AdditionController {

    private final AdditionRepository additionRepository;
    private final AdditionService additionService;

    @GetMapping(path = {"", "/"})
    public ResponseEntity<?> index() {
        return ResponseEntity.ok(additionRepository.findAll());
    }

    @GetMapping(path = {"/search/", "/search"})
    public ResponseEntity<?> searchAdditions(String query) {
        return ResponseEntity.ok(additionService.searchAddition(query));
    }

}
