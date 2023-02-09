package pl.tt.auth.service;

import pl.tt.auth.exception.InvalidTokenException;
import pl.tt.auth.model.*;

public interface JwtService {
    AuthenticationDTO auth(UsernamePasswordDTO usernamePasswordDTO);

    void logout(TokenDTO tokenDTO);

    AuthenticationDTO refreshToken(TokenDTO tokenDTO) throws InvalidTokenException;

    CheckTokenDTO checkToken(AccessTokenDTO accessTokenDTO);
}
