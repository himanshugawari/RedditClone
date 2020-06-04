package in.himanshugawari.reddit.security;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.time.Instant;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import in.himanshugawari.reddit.exceptions.SpringRedditException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class JwtProvider {

	private KeyStore keyStore;

	@Value(value = "${jwt.expiration.time}")
	private Long jwtExpirationInMillis;

	@PostConstruct
	public void init() {
		try {
			keyStore = KeyStore.getInstance("JKS");
			InputStream resourceAsStream = getClass().getResourceAsStream("/himanshuKey.jks");
			keyStore.load(resourceAsStream, "himanshuPassword".toCharArray());
		} catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException e) {
			throw new SpringRedditException("Exception occurred while loading keystore", e);
		}
	}

	public String generateToken(Authentication authentication) {
		User principal = (User) authentication.getPrincipal();
		return Jwts.builder().setSubject(principal.getUsername()).signWith(getPrivateKey())
				.setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationInMillis))).compact();
	}

	public String generateTokenWithUserName(String username) {
		return Jwts.builder().setSubject(username).setIssuedAt(Date.from(Instant.now())).signWith(getPrivateKey())
				.setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationInMillis))).compact();
	}

	private PrivateKey getPrivateKey() {
		try {
			return (PrivateKey) keyStore.getKey("himanshuKey", "himanshuPassword".toCharArray());
		} catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e) {
			throw new SpringRedditException("Exception occured while retrieving public key from keystore", e);
		}
	}

	public boolean validateToken(String jwt) {
		Jwts.parserBuilder().setSigningKey(getPublicKey()).build().parseClaimsJws(jwt);
		return true;
	}

	private PublicKey getPublicKey() {
		try {
			return (PublicKey) keyStore.getCertificate("himanshuKey").getPublicKey();
		} catch (KeyStoreException e) {
			throw new SpringRedditException("Exception occured while retrieving public key from keystore", e);
		}
	}

	public String getUsernameFromJwt(String token) {
		Claims claims = Jwts.parserBuilder().setSigningKey(getPublicKey()).build().parseClaimsJws(token).getBody();
		return claims.getSubject();
	}

	public Long getJwtExpirationInMillis() {
		return jwtExpirationInMillis;
	}
}
