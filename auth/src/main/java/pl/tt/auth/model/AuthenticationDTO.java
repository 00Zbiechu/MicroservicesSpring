package pl.tt.auth.model;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class AuthenticationDTO extends TokenDTO {

    private Long accessTokenValidityTime;
    private Long refreshTokenValidityTime;
}
