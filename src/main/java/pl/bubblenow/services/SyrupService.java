package pl.bubblenow.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.bubblenow.models.Syrup;
import pl.bubblenow.repositories.SyrupRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class SyrupService {
    private final SyrupRepository syrupRepository;

    public List<Syrup> searchSyrup(String query) {
        return syrupRepository.searchSyrups(query);
    }
}
