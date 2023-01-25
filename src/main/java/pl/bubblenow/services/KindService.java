package pl.bubblenow.services;

import org.springframework.stereotype.Service;
import pl.bubblenow.models.Kind;
import pl.bubblenow.repositories.KindRepository;

import java.util.List;

@Service
public class KindService {
    private KindRepository kindRepository;

    public KindService(KindRepository kindRepository) {
        this.kindRepository = kindRepository;
    }

    public List<Kind> searchKind(String query) {
        List<Kind> kinds = kindRepository.searchKinds(query);
        return kinds;
    }
}
