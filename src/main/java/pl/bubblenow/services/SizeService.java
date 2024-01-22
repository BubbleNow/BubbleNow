package pl.bubblenow.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.bubblenow.models.Size;
import pl.bubblenow.repositories.SizeRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class SizeService {
    private final SizeRepository sizeRepository;

    public List<Size> searchSize(String query) {
        return sizeRepository.searchSizes(query);
    }
}
