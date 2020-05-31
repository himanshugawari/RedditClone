package in.himanshugawari.reddit.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.himanshugawari.reddit.dto.RegisterRequest;
import in.himanshugawari.reddit.service.AuthService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = { "/api/auth/" })
@AllArgsConstructor
public class AuthController {

	private final AuthService authService;

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

}
