package in.himanshugawari.reddit.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.himanshugawari.reddit.dto.RegisterRequest;

@RestController
@RequestMapping(path = {"/api/auth/"})
public class AuthController {
	
	@PostMapping(path = {"/signup"})
	public void signup(@RequestBody RegisterRequest registerRequest) {
		
	}

}
