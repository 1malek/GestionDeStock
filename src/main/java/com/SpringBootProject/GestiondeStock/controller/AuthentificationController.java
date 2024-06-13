package com.SpringBootProject.GestiondeStock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBootProject.GestiondeStock.dto.auth.AuthenticationResponse;
import com.SpringBootProject.GestiondeStock.model.ExtendedUser;
import com.SpringBootProject.GestiondeStock.model.Utilisateur;
import com.SpringBootProject.GestiondeStock.repository.UtilisateurRepository;
import com.SpringBootProject.GestiondeStock.services.UtilisateurService;
import com.SpringBootProject.GestiondeStock.services.auth.ApplicationUserDetailsService;
import com.SpringBootProject.GestiondeStock.utils.JwtUtils;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor

public class AuthentificationController {
	
	private final UtilisateurService utilisateurService;
	private final JwtUtils jwtUtils;
	private final AuthenticationManager authenticationManager;
	private final UtilisateurRepository utilisateurRep;
	//private final ApplicationUserDetailsService userDetailsService;
	/* @PostMapping("/authenticate")
	  public ResponseEntity<AuthenticationResponse> authenticate(  @RequestBody AuthenticationResponse request) {
		  authenticationManager.authenticate(
			        new UsernamePasswordAuthenticationToken(
			            request.getLogin(),
			            request.getPassword()
			        )
			    );
			    final UserDetails userDetails = userDetailsService.loadUserByUserName(request.getLogin());

			    final String jwt = jwtUtils.generateToken( (ExtendedUser)userDetails);

			    return ResponseEntity.ok(AuthenticationResponse.builder().accessToken(jwt).build());
			  }*/

	  @PostMapping("/register")
	  public ResponseEntity<String> register(
	      @RequestBody final Utilisateur  dto) {
	    return ResponseEntity.ok(jwtUtils.generateToken(utilisateurService.save(dto)));
	  }
	  @PostMapping("/authenticate")
	  public ResponseEntity<String> authenticate(
	      @RequestBody Utilisateur request) {
		  authenticationManager.authenticate(
			        new UsernamePasswordAuthenticationToken(
			            request.getEmail(),
			            request.getPassword()
			        )
			    );
			    final UserDetails userDetails = utilisateurRep.findUtilisateurByEmail(request.getUsername());
                return ResponseEntity.ok(jwtUtils.generateToken( userDetails));
			  }
}
