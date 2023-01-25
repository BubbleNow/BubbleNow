package pl.bubblenow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.bubblenow.models.Syrup;

import java.util.List;

public interface SyrupRepository extends JpaRepository<Syrup, Integer> {
    List<Syrup> findAll();

    Syrup findById(int id);

    @Query("SELECT s FROM Syrup s WHERE " + "s.name LIKE CONCAT('%',:query, '%')")
    List<Syrup> searchSyrups(String query);
}
