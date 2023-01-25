package pl.bubblenow.controllers.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.bubblenow.repositories.SizeRepository;
import pl.bubblenow.services.SizeService;

@RestController("SizeRestController")
@RequestMapping(path = "/api/sizes")
public class SizeController {

    SizeRepository sizeRepository;
    SizeService sizeService;

    public SizeController(SizeRepository sizeRepository,
                          SizeService sizeService) {
        this.sizeRepository = sizeRepository;
        this.sizeService = sizeService;
    }

    @GetMapping(path = {"", "/"})
    public ResponseEntity<?> index() {
        return ResponseEntity.ok(sizeRepository.findAll());
    }

    @GetMapping(path = {"/search/", "/search"})
    public ResponseEntity<?> searchSizes(String query) {
        return ResponseEntity.ok(sizeService.searchSize(query));
    }


}
