package com.SpringBootProject.GestiondeStock.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.SpringBootProject.GestiondeStock.model.Utilisateur;
//@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur , Integer>{
	// @Query(value = "select u from Utilisateur u where u.email = :email")
	 Utilisateur findUtilisateurByEmail( String email);
// fl 9dim sta3mltha	Optional<Utilisateur> findUtilisateurByEmail(@Param("email") String email);
}

