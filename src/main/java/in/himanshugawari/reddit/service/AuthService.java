package in.himanshugawari.reddit.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import in.himanshugawari.reddit.dto.RegisterRequest;
import in.himanshugawari.reddit.model.User;
import in.himanshugawari.reddit.repository.UserRepository;

@Service
public class AuthService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	public void signup(RegisterRequest registerRequest) {
		User user=new User();
		user.setUserName(registerRequest.getUsername());
		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
		user.setEmail(registerRequest.getEmail());
		user.setUserCreated(Instant.now());
		user.setEnabled(false);
		userRepository.save(user);
	}
}
