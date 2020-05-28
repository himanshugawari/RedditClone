package in.himanshugawari.reddit.service;

import java.time.Instant;

import javax.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import in.himanshugawari.reddit.dto.RegisterRequest;
import in.himanshugawari.reddit.model.User;
import in.himanshugawari.reddit.repository.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {

	// @Autowired
	private final PasswordEncoder passwordEncoder;

	// @Autowired
	private final UserRepository userRepository;

	@Transactional
	public void signup(RegisterRequest registerRequest) {
		User user = new User();
		user.setUserName(registerRequest.getUsername());
		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
		user.setEmail(registerRequest.getEmail());
		user.setUserCreated(Instant.now());
		user.setEnabled(false);
		userRepository.save(user);
	}
}
