package pl.tt.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.tt.auth.exception.PersonNotFoundException;
import pl.tt.auth.model.PersonEntity;
import pl.tt.auth.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public PersonEntity findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new PersonNotFoundException(username));
    }
}
