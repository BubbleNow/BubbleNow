package pl.bubblenow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bubblenow.models.BubbleTea;

import java.util.List;

public interface BubbleTeaRepository extends JpaRepository<BubbleTea, Integer> {
    List<BubbleTea> findAll();

    BubbleTea save(BubbleTea entity);


}
