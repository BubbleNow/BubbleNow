package pl.bubblenow.services;

import org.springframework.stereotype.Service;
import pl.bubblenow.models.Milk;
import pl.bubblenow.repositories.MilkRepository;

import java.util.List;

@Service
public class MilkService {
    private MilkRepository milkRepository;

    public MilkService(MilkRepository milkRepository) {
        this.milkRepository = milkRepository;
    }

    public List<Milk> searchMilk(String query) {
        List<Milk> milks = milkRepository.searchMilks(query);
        return milks;
    }
}
