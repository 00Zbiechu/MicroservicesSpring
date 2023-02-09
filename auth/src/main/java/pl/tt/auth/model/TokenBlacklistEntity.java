package pl.tt.auth.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "token_blacklist")
public class TokenBlacklistEntity {
    @Id
    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "expire_date")
    private Timestamp expireDate;
}
