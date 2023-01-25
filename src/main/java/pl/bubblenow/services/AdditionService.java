package pl.bubblenow.services;

import org.springframework.stereotype.Service;
import pl.bubblenow.models.Addition;
import pl.bubblenow.repositories.AdditionRepository;

import java.util.List;

@Service
public class AdditionService {
    private AdditionRepository additionRepository;

    public AdditionService(AdditionRepository additionRepository) {
        this.additionRepository = additionRepository;
    }

    public List<Addition> searchAddition(String query) {
        List<Addition> additions = additionRepository.searchAdditions(query);
        return additions;
    }
}
