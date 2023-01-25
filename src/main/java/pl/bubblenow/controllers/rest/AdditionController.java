package pl.bubblenow.controllers.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.bubblenow.repositories.AdditionRepository;
import pl.bubblenow.services.AdditionService;

@RestController("AdditionRestController")
@RequestMapping(path = "/api/additions")
public class AdditionController {

    AdditionRepository additionRepository;
    AdditionService additionService;

    public AdditionController(AdditionRepository additionRepository, AdditionService additionService) {
        this.additionRepository = additionRepository;
        this.additionService = additionService;
    }

    @GetMapping(path = {"", "/"})
    public ResponseEntity<?> index() {
        return ResponseEntity.ok(additionRepository.findAll());
    }

    @GetMapping(path = {"/search/", "/search"})
    public ResponseEntity<?> searchAdditions(String query) {
        return ResponseEntity.ok(additionService.searchAddition(query));
    }

}
