package pl.bubblenow.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.bubblenow.models.Milk;
import pl.bubblenow.repositories.MilkRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class MilkService {
    private MilkRepository milkRepository;

    public List<Milk> searchMilk(String query) {
        return milkRepository.searchMilks(query);
    }
}
