package com.SpringBootProject.GestiondeStock.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBootProject.GestiondeStock.controller.api.CommandeFournisseurApi;
import com.SpringBootProject.GestiondeStock.dto.CommandeClientDto;
import com.SpringBootProject.GestiondeStock.dto.CommandeFournisseurDto;
import com.SpringBootProject.GestiondeStock.dto.LigneCommandeFournisseurDto;
import com.SpringBootProject.GestiondeStock.model.EtatCommande;
import com.SpringBootProject.GestiondeStock.services.CommandeClientService;
import com.SpringBootProject.GestiondeStock.services.CommandeFournisseurService;
@RestController
public class CommandeFournisseurController implements CommandeFournisseurApi{
	private CommandeFournisseurService commandeFournisseurService;
	@Autowired
	public CommandeFournisseurController(CommandeFournisseurService commandeFournisseurService){
		this.commandeFournisseurService=commandeFournisseurService;
	}
	@Override
	public ResponseEntity<CommandeFournisseurDto> save(CommandeFournisseurDto dto) {
		// TODO Auto-generated method stub
		return ResponseEntity.ok(commandeFournisseurService.save(dto));
	}

	@Override
	public ResponseEntity<CommandeFournisseurDto> findById(Integer id) {
		// TODO Auto-generated method stub
		return ResponseEntity.ok(commandeFournisseurService.findById(id));
	}


	@Override
	public ResponseEntity<List<CommandeFournisseurDto>> findAll() {
		// TODO Auto-generated method stub
		return ResponseEntity.ok(commandeFournisseurService.findAll());
	}

	@Override
	public ResponseEntity delete(Integer id) {
		commandeFournisseurService.delete(id);
		return ResponseEntity.ok().build();
	}
	@Override
	  public CommandeFournisseurDto updateEtatCommande(Integer idCommande, EtatCommande etatCommande) {
	    return commandeFournisseurService.updateEtatCommande(idCommande, etatCommande);
	  }

	  @Override
	  public CommandeFournisseurDto updateQuantiteCommande(Integer idCommande, Integer idLigneCommande, BigDecimal quantite) {
	    return commandeFournisseurService.updateQuantiteCommande(idCommande, idLigneCommande, quantite);
	  }

	  @Override
	  public CommandeFournisseurDto updateFournisseur(Integer idCommande, Integer idFournisseur) {
	    return commandeFournisseurService.updateFournisseur(idCommande, idFournisseur);
	  }

	  @Override
	  public CommandeFournisseurDto updateArticle(Integer idCommande, Integer idLigneCommande, Integer idArticle) {
	    return commandeFournisseurService.updateArticle(idCommande, idLigneCommande, idArticle);
	  }
	  @Override
	  public List<LigneCommandeFournisseurDto> findAllLignesCommandesFournisseurByCommandeFournisseurId(Integer idCommande) {
	    return commandeFournisseurService.findAllLignesCommandesFournisseurByCommandeFournisseurId(idCommande);
	  }
	  @Override
	  public CommandeFournisseurDto deleteArticle(Integer idCommande, Integer idLigneCommande) {
	    return commandeFournisseurService.deleteArticle(idCommande, idLigneCommande);
	  }
	@Override
	public CommandeFournisseurDto findByCode(String code) {
		return commandeFournisseurService.findByCode(code);
	}
	 
}
