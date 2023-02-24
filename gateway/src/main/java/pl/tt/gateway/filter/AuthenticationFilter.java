package pl.tt.gateway.filter;

import feign.FeignException;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ServerWebExchange;
import pl.tt.gateway.auth.feign.AuthFeignClient;
import pl.tt.gateway.auth.model.AccessTokenDTO;
import pl.tt.gateway.auth.model.CheckTokenDTO;
import reactor.core.publisher.Mono;

import java.io.IOException;

//@Component
@RequiredArgsConstructor
public class AuthenticationFilter implements GlobalFilter {
//    public static final String AUTHORIZATION_HEADER = "Authorization";
//    public static final String BEARER_PREFIX = "Bearer ";
//    private final AuthFeignClient authFeignClient;


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        String token = ((HttpServletRequest)exchange.getRequest()).getHeader(AUTHORIZATION_HEADER);
//
//        if (token.startsWith(BEARER_PREFIX)){
//            token = token.replace(BEARER_PREFIX, "");
//            ResponseEntity<CheckTokenDTO> checkTokenDTOResponseEntity =  authFeignClient.checkToken(AccessTokenDTO.builder().accessToken(token).build());
//
//            if (HttpStatus.OK == checkTokenDTOResponseEntity.getStatusCode() && checkTokenDTOResponseEntity.getBody().isValid()) {
//                return chain.filter(exchange);
//            }else
//                throw new UnauthorizedException();
//        }
        return null;
    }
}
