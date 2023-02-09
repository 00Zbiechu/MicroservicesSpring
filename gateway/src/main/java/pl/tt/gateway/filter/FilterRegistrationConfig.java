package pl.tt.gateway.filter;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FilterRegistrationConfig {
    //TODO cos nie dziala
    private final AuthenticationFilter authenticationFilter;

    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        return new FilterRegistrationBean(authenticationFilter);
    }
}
