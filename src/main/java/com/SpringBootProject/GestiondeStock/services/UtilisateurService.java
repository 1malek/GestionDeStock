package com.SpringBootProject.GestiondeStock.services;

import java.util.List;

import com.SpringBootProject.GestiondeStock.dto.UtilisateurDto;
import com.SpringBootProject.GestiondeStock.model.Utilisateur;

public interface UtilisateurService {
	public Utilisateur save(Utilisateur utilisateur);
	//public UtilisateurDto save(UtilisateurDto dto);
	//public UtilisateurDto findByEmail(String email);
	public Utilisateur findByEmail(String email);
	  UtilisateurDto findById(Integer id);

	  public List<UtilisateurDto> findAll();

	  void delete(Integer id);

	 
}
