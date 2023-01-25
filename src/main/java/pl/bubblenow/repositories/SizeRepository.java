package pl.bubblenow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.bubblenow.models.Size;

import java.util.List;

public interface SizeRepository extends JpaRepository<Size, Integer> {
    List<Size> findAll();

    Size findById(int id);

    @Query("SELECT s FROM Size s WHERE " + "s.name LIKE CONCAT('%',:query, '%')")
    List<Size> searchSizes(String query);
}
