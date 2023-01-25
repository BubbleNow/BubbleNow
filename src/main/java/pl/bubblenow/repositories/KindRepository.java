package pl.bubblenow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.bubblenow.models.Kind;

import java.util.List;

public interface KindRepository extends JpaRepository<Kind, Integer> {
    List<Kind> findAll();

    Kind findById(int id);

    @Query("SELECT k FROM Kind k WHERE " + "k.name LIKE CONCAT('%',:query, '%')")
    List<Kind> searchKinds(String query);
}
