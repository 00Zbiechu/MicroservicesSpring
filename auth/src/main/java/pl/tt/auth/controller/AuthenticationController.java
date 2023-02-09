package pl.tt.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.tt.auth.exception.InvalidTokenException;
import pl.tt.auth.model.*;
import pl.tt.auth.service.JwtService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

	private final JwtService jwtService;

	@PostMapping
	public ResponseEntity<AuthenticationDTO> authenticate(@RequestBody UsernamePasswordDTO usernamePasswordDTO){
		return ResponseEntity.ok(jwtService.auth(usernamePasswordDTO));
	}
	@PostMapping("/logout")
	public ResponseEntity<Void> logout(@RequestBody TokenDTO tokenDTO){
		jwtService.logout(tokenDTO);
		return ResponseEntity.ok().build();
	}
	@PostMapping("/refreshToken")
	public ResponseEntity<AuthenticationDTO> refreshToken(@RequestBody TokenDTO tokenDTO) throws InvalidTokenException {
		return ResponseEntity.ok(jwtService.refreshToken(tokenDTO));
	}
	@PostMapping("/checkToken")
	public ResponseEntity<CheckTokenDTO> checkToken(@RequestBody AccessTokenDTO accessTokenDTO) {
		return ResponseEntity.ok(jwtService.checkToken(accessTokenDTO));
	}
}
