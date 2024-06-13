package com.SpringBootProject.GestiondeStock.services.auth;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.SpringBootProject.GestiondeStock.dto.UtilisateurDto;
import com.SpringBootProject.GestiondeStock.exception.ErrorCodes;
import com.SpringBootProject.GestiondeStock.model.ExtendedUser;
import com.SpringBootProject.GestiondeStock.model.Utilisateur;
import com.SpringBootProject.GestiondeStock.repository.UtilisateurRepository;
import com.SpringBootProject.GestiondeStock.services.UtilisateurService;

import jakarta.persistence.EntityNotFoundException;
@Service
public class ApplicationUserDetailsService implements UserDetailsService {

	 @Autowired
	  private UtilisateurService service;


	  @Override
	  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	    Utilisateur utilisateur = service.findByEmail( email);

	 List <SimpleGrantedAuthority> authorities = new ArrayList <>();
	    utilisateur.getRole().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName())));
		//  return new User("", "",Collections.emptyList());
	    return new ExtendedUser(utilisateur.getEmail(), utilisateur.getPassword(),utilisateur.getEntreprise().getId(), authorities);
	  }
}
