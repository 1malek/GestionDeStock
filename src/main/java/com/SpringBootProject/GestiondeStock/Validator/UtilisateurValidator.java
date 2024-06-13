package com.SpringBootProject.GestiondeStock.Validator;
import java.util.List;
import java.util.ArrayList;
import org.springframework.util.StringUtils;
import com.SpringBootProject.GestiondeStock.dto.UtilisateurDto;

public class UtilisateurValidator {
	public static List<String> validate(UtilisateurDto utilisateurDto) {
	    List<String> errors = new ArrayList<>();

	    if (utilisateurDto == null) {
	      errors.add("Veuillez renseigner le nom d'utilisateur");
	      errors.add("Veuillez renseigner le prenom d'utilisateur");
	      errors.add("Veuillez renseigner le mail d'utilisateur");
	      errors.add("Veuillez renseigner le mot de passe d'utilisateur");
	      errors.add("Veuillez renseigner l'adresse d'utilisateur");
	      return errors;
	    }

	    if (!StringUtils.hasLength(utilisateurDto.getNom())) {
	      errors.add("Veuillez renseigner le nom d'utilisateur");
	    }
	    if (!StringUtils.hasLength(utilisateurDto.getPrenom())) {
	      errors.add("Veuillez renseigner le prenom d'utilisateur");
	    }
	    if (!StringUtils.hasLength(utilisateurDto.getEmail())) {
	      errors.add("Veuillez renseigner l'email d'utilisateur");
	    }
	    if (!StringUtils.hasLength(utilisateurDto.getPassword())) {
	      errors.add("Veuillez renseigner le mot de passe d'utilisateur");
	    }
	    if (utilisateurDto.getDateDeNaissance() == null) {
		      errors.add("Veuillez renseigner le mot de passe d'utilisateur");
		    }
	    if (utilisateurDto.getAdresse() == null) {
		      errors.add("Veuillez renseigner l'adresse d'utilisateur");}
	    else {
	    	 if (!StringUtils.hasLength(utilisateurDto.getAdresse().getAdresse1())) {
	   	      errors.add("Veuillez renseigner le mot de passe d'utilisateur");
	    }
	    	 if (!StringUtils.hasLength(utilisateurDto.getAdresse().getCodePostale())) {
		   	      errors.add("Veuillez renseigner le mot de passe d'utilisateur");
		    }
	    	 if (!StringUtils.hasLength(utilisateurDto.getAdresse().getVille())) {
		   	      errors.add("Veuillez renseigner le mot de passe d'utilisateur");
		    }}
	    
return errors;
}
	}