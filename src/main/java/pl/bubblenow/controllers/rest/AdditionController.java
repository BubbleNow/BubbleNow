package pl.bubblenow.controllers.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.bubblenow.repositories.AdditionRepository;

@RestController
@RequestMapping(path = "/additions")
public class AdditionController {

    AdditionRepository additionRepository;

    public AdditionController(AdditionRepository additionRepository) {
        this.additionRepository = additionRepository;
    }

    @GetMapping(path = {"","/"})
    public ResponseEntity<?> index(){
        return ResponseEntity.ok(additionRepository.findAll());
    }

}
