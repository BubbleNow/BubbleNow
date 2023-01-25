package pl.bubblenow.controllers.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.bubblenow.repositories.SyrupRepository;
import pl.bubblenow.services.SyrupService;

@RestController("SyrupRestController")
@RequestMapping(path = "/api/syrups")
public class SyrupController {

    SyrupRepository syrupRepository;
    SyrupService syrupService;

    public SyrupController(SyrupRepository syrupRepository,
                           SyrupService syrupService) {
        this.syrupRepository = syrupRepository;
        this.syrupService = syrupService;
    }

    @GetMapping(path = {"", "/"})
    public ResponseEntity<?> index() {
        return ResponseEntity.ok(syrupRepository.findAll());
    }

    @GetMapping(path = {"/search/", "/search"})
    public ResponseEntity<?> searchSyrups(String query) {
        return ResponseEntity.ok(syrupService.searchSyrup(query));
    }


}
