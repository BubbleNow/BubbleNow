package pl.bubblenow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bubblenow.models.Addition;

import java.util.List;
import java.util.Optional;

public interface AdditionRepository extends JpaRepository<Addition, Integer> {
    List<Addition> findAll();
    Addition findById(int id);
}
