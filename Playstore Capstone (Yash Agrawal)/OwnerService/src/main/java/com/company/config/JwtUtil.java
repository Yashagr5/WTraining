//package com.company.config;
//
//import java.util.Date;
//
//import org.springframework.stereotype.Component;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//
//@Component
//public class JwtUtil {
//
//    private final String SECRET_KEY = "mySecretKey12345"; // Use env variable in prod
//    private final long EXPIRATION_MS = 1000 * 60 * 60 * 10; // 10 hours
//
//    // Generate JWT token
//    public String generateToken(String email) {
//        return Jwts.builder()
//                .setSubject(email)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
//                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
//                .compact();
//    }
//
//    // Validate token
//    public boolean validateToken(String token, String email) {
//        return extractEmail(token).equals(email) && !isTokenExpired(token);
//    }
//
//    public String extractEmail(String token) {
//        return extractAllClaims(token).getSubject();
//    }
//
//    private boolean isTokenExpired(String token) {
//        return extractAllClaims(token).getExpiration().before(new Date());
//    }
//
//    private Claims extractAllClaims(String token) {
//        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
//    }
//
//	public String extractUsername(String token) {
//		// Parse the JWT token using the secret key
//	    Claims claims = Jwts.parser()
//	            .setSigningKey("your-secret-key") // use the same secret used to sign the token
//	            .parseClaimsJws(token)
//	            .getBody();
//
//	    // The username (or identifier) is usually stored in the subject
//	    return claims.getSubject();
//	}
//}
