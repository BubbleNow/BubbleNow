package pl.bubblenow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.bubblenow.models.Milk;

import java.util.List;

public interface MilkRepository extends JpaRepository<Milk, Integer> {
    List<Milk> findAll();

    Milk findById(int id);

    @Query("SELECT m FROM Milk m WHERE " + "m.name LIKE CONCAT('%',:query, '%')")
    List<Milk> searchMilks(String query);
}
