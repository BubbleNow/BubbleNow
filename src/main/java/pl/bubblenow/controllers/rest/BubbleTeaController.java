package pl.bubblenow.controllers.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.bubblenow.repositories.BubbleTeaRepository;

@RestController
@RequestMapping(path = "/bubble-teas")
public class BubbleTeaController {

    BubbleTeaRepository bubbleTeaRepository;

    public BubbleTeaController(BubbleTeaRepository bubbleTeaRepository) {
        this.bubbleTeaRepository = bubbleTeaRepository;
    }

    @GetMapping(path = {"", "/"})
    public ResponseEntity<?> index() {
        return ResponseEntity.ok(bubbleTeaRepository.findAll());
    }

}
