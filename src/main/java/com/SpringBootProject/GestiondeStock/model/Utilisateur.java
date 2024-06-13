package com.SpringBootProject.GestiondeStock.model;

import java.time.Instant;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
// @EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "utilisateur")

public class Utilisateur implements UserDetails{
	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer id;
	@Column(name="nom")
private String nom;
	@Column(name="prenom")
	private String prenom;
	@Column(name="email")
	private String email;
	@Column(name="datedenaissance")
	private Instant dateDeNaissance;
	@Column(name="password")
	private String password;
	@Embedded
	private Adresse adresse;
	@Column(name="photo")
	private String photo;
	@ManyToOne
	@JoinColumn(name="identreprise")
	private Entreprise entreprise;
	@OneToMany(fetch=FetchType.EAGER,mappedBy="utilisateur")
	@JsonIgnore
	private List<Role> role;
	@OneToMany(mappedBy = "utilisateur")
	//  private List<Token> tokens;
	 
	  @Override
	  public Collection<? extends GrantedAuthority> getAuthorities() {
	    return Collections.singletonList(new SimpleGrantedAuthority("role"));
	  }

	  @Override
	  public String getPassword() {
	    return password;
	  }

	  @Override
	  public String getUsername() {
	    return email;
	  }

	  @Override
	  public boolean isAccountNonExpired() {
	    return true;
	  }

	  @Override
	  public boolean isAccountNonLocked() {
	    return true;
	  }

	  @Override
	  public boolean isCredentialsNonExpired() {
	    return true;
	  }

	  @Override
	  public boolean isEnabled() {
	    return true;
	  }
}
