package pl.tt.auth.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.tt.auth.model.PersonEntity;
import pl.tt.auth.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class UserActiveService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @PostConstruct
    public void initUser(){
        userRepository.deleteAll();

        PersonEntity personEntity = PersonEntity.builder()
                .active(1)
                .email("asdf@email.com")
                .password(passwordEncoder.encode("zaq1@WSX"))
                .username("haslo_okon")
                .build();
        userRepository.save(personEntity);
    }
}
