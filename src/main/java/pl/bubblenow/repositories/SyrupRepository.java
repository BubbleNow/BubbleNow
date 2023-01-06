package pl.bubblenow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bubblenow.models.Syrup;

import java.util.List;

public interface SyrupRepository extends JpaRepository<Syrup, Integer> {

    List<Syrup> findAll();
}
