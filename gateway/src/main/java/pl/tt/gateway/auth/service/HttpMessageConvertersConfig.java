package pl.tt.gateway.auth.service;

import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;

@Configuration
public class HttpMessageConvertersConfig {

	@Bean
	public HttpMessageConverters httpMessageConverters(){
		return new HttpMessageConverters();
	}
}
