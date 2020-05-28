package in.himanshugawari.reddit.service;

import java.time.Instant;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import in.himanshugawari.reddit.dto.RegisterRequest;
import in.himanshugawari.reddit.model.User;
import in.himanshugawari.reddit.model.VerificationToken;
import in.himanshugawari.reddit.repository.UserRepository;
import in.himanshugawari.reddit.repository.VerificationTokenRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {

	// @Autowired
	private final PasswordEncoder passwordEncoder;

	// @Autowired
	private final UserRepository userRepository;
	
	private final VerificationTokenRepository verificationTokenRepository;

	@Transactional
	public void signup(RegisterRequest registerRequest) {
		User user = new User();
		user.setUserName(registerRequest.getUsername());
		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
		user.setEmail(registerRequest.getEmail());
		user.setUserCreated(Instant.now());
		user.setEnabled(false);
		userRepository.save(user);
		
		String token=generateVerificationToken(user);
	}
	
	private String generateVerificationToken(User user) {
		String token=UUID.randomUUID().toString();
		VerificationToken verificationToken=new VerificationToken();
		verificationToken.setToken(token);
		verificationToken.setUser(user);
		verificationTokenRepository.save(verificationToken);
		return token;
		
	}
}
