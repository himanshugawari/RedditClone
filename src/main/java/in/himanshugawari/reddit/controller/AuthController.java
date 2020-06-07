package in.himanshugawari.reddit.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.himanshugawari.reddit.dto.AuthenticationResponse;
import in.himanshugawari.reddit.dto.LoginRequest;
import in.himanshugawari.reddit.dto.RefreshTokenRequest;
import in.himanshugawari.reddit.dto.RegisterRequest;
import in.himanshugawari.reddit.service.AuthService;
import in.himanshugawari.reddit.service.RefreshTokenService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = { "/api/auth/" })
@AllArgsConstructor
public class AuthController {

	private final AuthService authService;
	private final RefreshTokenService refreshTokenService;

	@PostMapping(path = { "/signup" })
	public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) {
		authService.signup(registerRequest);
		return new ResponseEntity<>("User Registration Successful", HttpStatus.OK);
	}

	@GetMapping(path = { "accountVerification/{token}" })
	public ResponseEntity<String> verifyAccount(@PathVariable String token) {
		authService.verifyAccount(token);
		return new ResponseEntity<>("Account activated Successfully", HttpStatus.OK);
	}

	@PostMapping(path = { "/login" })
	public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
		return authService.login(loginRequest);
	}

	@PostMapping(path = { "/refresh/token" })
	public AuthenticationResponse refreshTokens(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
		return authService.refreshToken(refreshTokenRequest);
	}

	@PostMapping(path = { "/logout" })
	public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
		refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
		return ResponseEntity.status(HttpStatus.OK).body("Refresh Token Deleted Successfully!!");
	}

}
