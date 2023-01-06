package pl.bubblenow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bubblenow.models.Kind;

import java.util.List;

public interface KindRepository extends JpaRepository<Kind, Integer> {

    List<Kind> findAll();
}
