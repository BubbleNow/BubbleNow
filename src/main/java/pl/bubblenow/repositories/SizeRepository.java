package pl.bubblenow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bubblenow.models.Addition;
import pl.bubblenow.models.Size;

import java.util.List;

public interface SizeRepository extends JpaRepository<Size, Integer> {
    List<Size> findAll();

    Size findById(int id);
}
