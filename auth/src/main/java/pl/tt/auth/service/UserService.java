package pl.tt.auth.service;

import pl.tt.auth.model.PersonEntity;

public interface UserService {

    PersonEntity findByUsername(String username);
}
