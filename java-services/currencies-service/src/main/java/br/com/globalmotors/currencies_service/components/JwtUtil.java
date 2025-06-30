package br.com.globalmotors.currencies_service.components;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtUtil {
	private static final String SECRET_KEY = "chaveSecretaGlobalMotors";
	
	private static SecretKey getSigninKey() {
		return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
	}
	
	public static Claims validateToken(String token) {
		try {
			return Jwts.parser()
					.verifyWith(getSigninKey())
					.build()
					.parseSignedClaims(token)
					.getPayload();
		} catch (Exception e) {
			return null;
		}
	}
}
