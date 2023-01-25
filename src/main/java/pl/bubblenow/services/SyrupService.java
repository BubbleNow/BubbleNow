package pl.bubblenow.services;

import org.springframework.stereotype.Service;
import pl.bubblenow.models.Syrup;
import pl.bubblenow.repositories.SyrupRepository;

import java.util.List;

@Service
public class SyrupService {
    private SyrupRepository syrupRepository;

    public SyrupService(SyrupRepository syrupRepository) {
        this.syrupRepository = syrupRepository;
    }

    public List<Syrup> searchSyrup(String query) {
        List<Syrup> syrups = syrupRepository.searchSyrups(query);
        return syrups;
    }
}
