package pl.bubblenow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.bubblenow.models.Addition;

import java.util.List;

public interface AdditionRepository extends JpaRepository<Addition, Integer> {
    List<Addition> findAll();

    Addition findById(int id);

    Addition deleteById(int id);

    @Query("SELECT a FROM Addition a WHERE " + "a.name LIKE CONCAT('%',:query, '%')")
    List<Addition> searchAdditions(String query);

}
