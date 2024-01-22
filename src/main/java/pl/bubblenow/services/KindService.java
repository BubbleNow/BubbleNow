package pl.bubblenow.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import pl.bubblenow.models.Kind;
import pl.bubblenow.repositories.KindRepository;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class KindService {
    private final KindRepository kindRepository;

    public List<Kind> searchKind(String query) {
        return kindRepository.searchKinds(query);
    }

    public void uploadImage(Kind kind, MultipartFile image) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(image.getOriginalFilename()));
        String uploadDir = "src\\main\\resources\\static\\uploads\\";

        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = image.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            kind.setFilePath(filePath.getFileName().toString());
        } catch (IOException e) {
            throw new IOException("Nie mozna bylo zapisac pliku:" + fileName);
        }
        kindRepository.save(kind);
    }
}
