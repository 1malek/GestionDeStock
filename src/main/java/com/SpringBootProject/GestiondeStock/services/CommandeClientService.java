package com.SpringBootProject.GestiondeStock.services;

import java.util.List;

import java.math.BigDecimal;
import com.SpringBootProject.GestiondeStock.dto.CommandeClientDto;
import com.SpringBootProject.GestiondeStock.dto.LigneCommandeClientDto;
import com.SpringBootProject.GestiondeStock.model.EtatCommande;

public interface CommandeClientService {
	CommandeClientDto save(CommandeClientDto dto);
	
	CommandeClientDto updateEtatCommande(Integer idCommande, EtatCommande etatCommande);
	
	CommandeClientDto findById(Integer id);
	
 CommandeClientDto updateQuantiteCommande(Integer idCommande, Integer idLigneCommande, BigDecimal quantite);
 
 CommandeClientDto updateArticle(Integer idCommande, Integer idLigneCommande , Integer idArticle);
 
 CommandeClientDto updateClient(Integer idCommande, Integer idClient);
 
	CommandeClientDto findByCodeArticle(String code);

	  List<CommandeClientDto> findAll();
	  List<LigneCommandeClientDto> findAllLignesCommandesClientByCommandeClientId(Integer idCommande);
	  // Delete article ==> delete LigneCommandeClient
	  CommandeClientDto deleteArticle(Integer idCommande, Integer idLigneCommande);
	  
	  void delete(Integer id);
}
