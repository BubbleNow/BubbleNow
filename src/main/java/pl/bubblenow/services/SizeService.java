package pl.bubblenow.services;

import org.springframework.stereotype.Service;
import pl.bubblenow.models.Size;
import pl.bubblenow.repositories.SizeRepository;

import java.util.List;

@Service
public class SizeService {
    private SizeRepository sizeRepository;

    public SizeService(SizeRepository sizeRepository) {
        this.sizeRepository = sizeRepository;
    }

    public List<Size> searchSize(String query) {
        List<Size> sizes = sizeRepository.searchSizes(query);
        return sizes;
    }
}
