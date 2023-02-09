package pl.tt.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.tt.auth.model.TokenBlacklistEntity;

@Repository
public interface TokenBlacklistRepository extends JpaRepository<TokenBlacklistEntity, String> {
}
