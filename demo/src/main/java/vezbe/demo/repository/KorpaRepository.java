package vezbe.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vezbe.demo.model.Korpa;

@Repository
public interface KorpaRepository extends JpaRepository<Korpa, Long> {
}
