package com.SpringBootProject.GestiondeStock.services;

import java.math.BigDecimal;
import java.util.List;

import com.SpringBootProject.GestiondeStock.dto.CommandeFournisseurDto;
import com.SpringBootProject.GestiondeStock.dto.LigneCommandeFournisseurDto;
import com.SpringBootProject.GestiondeStock.model.EtatCommande;

public interface CommandeFournisseurService {
	
	CommandeFournisseurDto save(CommandeFournisseurDto dto);

	CommandeFournisseurDto updateEtatCommande(Integer idCommande, EtatCommande etatCommande);

	  CommandeFournisseurDto updateQuantiteCommande(Integer idCommande, Integer idLigneCommande, BigDecimal quantite);

	  CommandeFournisseurDto updateFournisseur(Integer idCommande, Integer idFournisseur);

	  CommandeFournisseurDto updateArticle(Integer idCommande, Integer idLigneCommande, Integer idArticle);

	  // Delete article ==> delete LigneCommandeFournisseur
	  CommandeFournisseurDto deleteArticle(Integer idCommande, Integer idLigneCommande);


	  CommandeFournisseurDto findById(Integer id);

	  CommandeFournisseurDto findByCode(String code);

	  List<CommandeFournisseurDto> findAll();

	  List<LigneCommandeFournisseurDto> findAllLignesCommandesFournisseurByCommandeFournisseurId(Integer idCommande);

	  void delete(Integer id);
}
