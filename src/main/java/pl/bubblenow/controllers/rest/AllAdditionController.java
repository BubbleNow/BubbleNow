package pl.bubblenow.controllers.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.bubblenow.models.*;
import pl.bubblenow.repositories.*;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(path = "/api/all")
@AllArgsConstructor
public class AllAdditionController {

    private final AdditionRepository additionRepository;
    private final KindRepository kindRepository;
    private final MilkRepository milkRepository;
    private final SizeRepository sizeRepository;
    private final SyrupRepository syrupRepository;


    @GetMapping(path = {"", "/"})
    public ResponseEntity<?> index() {

        HashMap<String, List<?>> hashMap = new HashMap<>();
        hashMap.put("additions", additionRepository.findAll().stream().toList());
        hashMap.put("milks", milkRepository.findAll().stream().toList());
        hashMap.put("sizes", sizeRepository.findAll().stream().toList());
        hashMap.put("kinds", kindRepository.findAll().stream().toList());
        hashMap.put("syrups", syrupRepository.findAll().stream().toList());

        return ResponseEntity.ok(hashMap);
    }
}
