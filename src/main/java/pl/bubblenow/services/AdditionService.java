package pl.bubblenow.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.bubblenow.models.Addition;
import pl.bubblenow.repositories.AdditionRepository;

import java.io.IOException;
import java.util.List;


@Service
@AllArgsConstructor
public class AdditionService {
    private final AdditionRepository additionRepository;
    private final ImageService imageService;

    public List<Addition> searchAddition(String query) {
        return additionRepository.searchAdditions(query);
    }

    public void uploadImage(Addition addition, MultipartFile image) throws IOException {

        addition.setFilePath(imageService.uploadImage(image));

        additionRepository.save(addition);
    }
}
