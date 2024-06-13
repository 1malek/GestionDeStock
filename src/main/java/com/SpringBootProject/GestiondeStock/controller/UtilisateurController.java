package com.SpringBootProject.GestiondeStock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBootProject.GestiondeStock.controller.api.UtilisateurApi;
import com.SpringBootProject.GestiondeStock.dto.UtilisateurDto;
import com.SpringBootProject.GestiondeStock.model.Utilisateur;
import com.SpringBootProject.GestiondeStock.services.UtilisateurService;
@RestController
public class UtilisateurController implements UtilisateurApi {

	  private UtilisateurService utilisateurService;

	  @Autowired
	  public UtilisateurController(UtilisateurService utilisateurService) {
	    this.utilisateurService = utilisateurService;
	  }

	  @Override
	  public Utilisateur save(Utilisateur utilisateur) {
	    return utilisateurService.save(utilisateur);
	  }

	//  @Override
	 // public UtilisateurDto changerMotDePasse(ChangerMotDePasseUtilisateurDto dto) {
	  //  return utilisateurService.changerMotDePasse(dto);
	  //}

	  @Override
	  public UtilisateurDto findById(Integer id) {
	    return utilisateurService.findById(id);
	  }

	
	 @Override
	public List<UtilisateurDto> findAll() {
	  return utilisateurService.findAll();
	 }

	  @Override
	public void delete(Integer id) {
		    utilisateurService.delete(id);
	}

	@Override
	public Utilisateur findByEmail(String email) {
		// TODO Auto-generated method stub
		return utilisateurService.findByEmail(email);
	}

}
