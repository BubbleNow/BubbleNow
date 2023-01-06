package pl.bubblenow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bubblenow.models.Addition;

import java.util.List;

public interface AdditionRepository extends JpaRepository<Addition, Integer> {
    @Override
    List<Addition> findAll();
}
