package pl.bubblenow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bubblenow.models.Addition;
import pl.bubblenow.models.Base;

import java.util.List;

public interface BaseRepository extends JpaRepository<Base, Integer> {
    List<Base> findAll();
    Base findById(int id);
}
