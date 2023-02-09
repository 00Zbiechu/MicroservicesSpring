package pl.tt.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.tt.auth.model.PersonEntity;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<PersonEntity, Long> {
    Optional<PersonEntity> findByUsername(String username);
}
