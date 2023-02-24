package pl.tt.gateway.filter;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FilterRegistrationConfig {


//    @Bean
//    public FilterRegistrationBean<AuthenticationFilter> loginFilter(){
//
//        FilterRegistrationBean<AuthenticationFilter> filter = new FilterRegistrationBean<>();
//
//        filter.setFilter(new AuthenticationFilter());
//
//        return filter;
//
//    }
}
