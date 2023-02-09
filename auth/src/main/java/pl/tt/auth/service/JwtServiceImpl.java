package pl.tt.auth.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.tt.auth.controller.JwtProperties;
import pl.tt.auth.exception.InvalidTokenException;
import pl.tt.auth.exception.NotAuthorizedException;
import pl.tt.auth.exception.TokenUserMismatchException;
import pl.tt.auth.model.*;
import pl.tt.auth.repository.TokenBlacklistRepository;

import java.sql.Timestamp;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static io.jsonwebtoken.SignatureAlgorithm.HS512;

@Service
@Slf4j
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_CLAIMS = "role";
    private final TokenBlacklistRepository tokenBlacklistRepository;
    private final JwtProperties jwtProperties;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Value("${spring.application.name}")
    private String applicationName;

    @Override
    public AuthenticationDTO auth(UsernamePasswordDTO usernamePasswordDTO) {
        PersonEntity userEntity = userService.findByUsername(usernamePasswordDTO.getUsername());

        if (!passwordEncoder.matches(usernamePasswordDTO.getPassword(), userEntity.getPassword())) {
            throw new NotAuthorizedException("Password wrong");
        }

        return buildAuthenicationToken(userEntity);
    }

    @Override
    public void logout(TokenDTO tokenDTO) {
        try{
            blockTokens(tokenDTO);
        }catch(InvalidTokenException e){
            log.debug("invalid token");
        }


    }
    private void blockTokens(TokenDTO tokenDTO) throws InvalidTokenException {
        blockToken(tokenDTO.getAccessToken(), jwtProperties.getAccessTokenSecret());
        blockToken(tokenDTO.getRefreshToken(), jwtProperties.getRefreshTokenSecret());
    }

    @Override
    public AuthenticationDTO refreshToken(TokenDTO tokenDTO) throws InvalidTokenException {
        blockTokens(tokenDTO);
        PersonEntity userEntity = getUserByAccessToken(tokenDTO);


        return buildAuthenicationToken(userEntity);
    }

    @Override
    public CheckTokenDTO checkToken(AccessTokenDTO accessTokenDTO) {
        try {
            String userName = getUsernameFromToken(accessTokenDTO.getAccessToken(), jwtProperties.getAccessTokenSecret());
            if (!tokenBlacklistRepository.existsById(accessTokenDTO.getAccessToken())){
                return CheckTokenDTO.builder().isValid(true).build();
            }
        }catch(Exception ex){
        }
        return CheckTokenDTO.builder().isValid(Boolean.FALSE).build();
    }

    private AuthenticationDTO buildAuthenicationToken(PersonEntity userEntity) {
        return AuthenticationDTO.builder()
                .accessToken(generateAccessToken(userEntity))
                .accessTokenValidityTime(jwtProperties.getAccessTokenValidityTime())
                .refreshToken(generateRefreshToken(userEntity))
                .refreshTokenValidityTime(jwtProperties.getRefreshTokenValidityTime())
                .build();
    }

    private PersonEntity getUserByAccessToken(TokenDTO tokenDTO) {
        String acessTokenUserName = getUsernameFromToken(tokenDTO.getAccessToken(), jwtProperties.getAccessTokenSecret());
        String refreshTokenUserName = getUsernameFromToken(tokenDTO.getRefreshToken(), jwtProperties.getRefreshTokenSecret());
        if (!StringUtils.equals(acessTokenUserName, refreshTokenUserName)) {
            throw new TokenUserMismatchException(String.format("token coś nie pasuje"));
        }
        PersonEntity userEntity = userService.findByUsername(acessTokenUserName);
        return userEntity;
    }

    private void blockToken(String token, String secret) throws InvalidTokenException {
        if(!StringUtils.isEmpty(token)) {
            try {
                Jws<Claims> tokenClaims = Jwts.parser()
                        .setSigningKey(secret)
                        .parseClaimsJws(token);
                TokenBlacklistEntity tokenToBlock = TokenBlacklistEntity.builder()
                        .token(token)
                        .expireDate(Timestamp.from(tokenClaims.getBody().getExpiration().toInstant()))
                        .build();
                tokenBlacklistRepository.save(tokenToBlock);
            } catch (Exception e) {
                log.debug("invalid token" + token);
                throw new InvalidTokenException("invalid token" + token);
            }
        }

    }

    private String getUsernameFromToken(String token, String secret) {
        Jws<Claims> tokenClaims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token);
        return tokenClaims.getBody().getSubject();
    }

    private String generateAccessToken(PersonEntity userEntity) {
        Date now = new Date();
        Date expirationDate = Date.from(now.toInstant().plus(jwtProperties.getAccessTokenValidityTime(), ChronoUnit.MILLIS));
        Map<String, Object> claims = new HashedMap<>();
        claims.put(ROLE_CLAIMS, List.of(ROLE_USER));
        return Jwts.builder()
                .addClaims(claims)
                .setIssuer(applicationName)
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .setSubject(userEntity.getUsername())
                .signWith(HS512, jwtProperties.getAccessTokenSecret())
                .compact();
    }

    private String generateRefreshToken(PersonEntity userEntity) {
        Date now = new Date();
        Date expirationDate = Date.from(now.toInstant().plus(jwtProperties.getRefreshTokenValidityTime(), ChronoUnit.MILLIS));
        return Jwts.builder()
                .setIssuer(applicationName)
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .setSubject(userEntity.getUsername())
                .signWith(HS512, jwtProperties.getRefreshTokenSecret())
                .compact();
    }


}
