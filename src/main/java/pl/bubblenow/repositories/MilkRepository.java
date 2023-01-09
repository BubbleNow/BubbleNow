package pl.bubblenow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bubblenow.models.Milk;

import java.util.List;

public interface MilkRepository extends JpaRepository<Milk, Integer> {
    List<Milk> findAll();
    Milk findById(int id);
}
