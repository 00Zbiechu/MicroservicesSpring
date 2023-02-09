package pl.tt.gateway.auth.model;

import lombok.Value;

@Value
public class AuthDTO {
	private String username;
	private String password;
}
