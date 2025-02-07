package com.SpringBootProject.GestiondeStock.utils;

import org.springframework.stereotype.Service;

import com.SpringBootProject.GestiondeStock.model.ExtendedUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
@Service
public class JwtUtils {
	private String jwtSECRET_KEY = "secret";

	  public String extractUsername(String token) {
	    return extractClaim(token, Claims::getSubject);
	  }

	  public Date extractExpiration(String token) {
	    return extractClaim(token, Claims::getExpiration);
	  }

	  public String extractIdEntreprise(String token) {
	    final Claims claims = extractAllClaims(token);

	    return claims.get("idEntreprise", String.class);
	  }
public boolean hasClaim(String token , String claimName) {
	 final Claims claims = extractAllClaims(token);
	 return claims.get(claimName)!=null;
}
	  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
	    final Claims claims = extractAllClaims(token);
	    return claimsResolver.apply(claims);
	  }

	  private Claims extractAllClaims(String token) {
	    return Jwts.parser().setSigningKey(jwtSECRET_KEY).parseClaimsJws(token).getBody();
	  }

	  private Boolean isTokenExpired(String token) {
	    return extractExpiration(token).before(new Date());
	  }

	  public String generateToken(UserDetails userDetails) {
	    Map<String, Object> claims = new HashMap<>();
	    return createToken(claims, userDetails);
	  }

	  private String createToken(Map<String, Object> claims, UserDetails userDetails) {

	    return Jwts.builder().setClaims(claims)
	    		.setSubject(userDetails.getUsername())
	        .setIssuedAt(new Date(System.currentTimeMillis()))
	        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
	        .claim("idEntreprise", "")
	        .signWith(SignatureAlgorithm.HS256, jwtSECRET_KEY).compact();
	  }

	  public Boolean validateToken(String token, UserDetails userDetails) {
	    final String username = extractUsername(token);
	    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	  }
}
