package com.SpringBootProject.GestiondeStock.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBootProject.GestiondeStock.controller.api.CommandeClientApi;
import com.SpringBootProject.GestiondeStock.dto.CommandeClientDto;
import com.SpringBootProject.GestiondeStock.dto.LigneCommandeClientDto;
import com.SpringBootProject.GestiondeStock.model.EtatCommande;
import com.SpringBootProject.GestiondeStock.services.CommandeClientService;
@RestController
public abstract class CommandeClientController implements CommandeClientApi{
private CommandeClientService commandeClientService;
@Autowired
public CommandeClientController(CommandeClientService commandeClientService){
	this.commandeClientService=commandeClientService;
}
	@Override
	public ResponseEntity<CommandeClientDto>save(CommandeClientDto dto) {
		// TODO Auto-generated method stub
		return ResponseEntity.ok(commandeClientService.save(dto));
	}

	@Override
	public ResponseEntity<CommandeClientDto> findById(Integer id) {
		// TODO Auto-generated method stub
		return ResponseEntity.ok(commandeClientService.findById(id));
	}

	@Override
	public ResponseEntity<CommandeClientDto> findByCodeArticle(String code) {
		// TODO Auto-generated method stub
		return ResponseEntity.ok(commandeClientService.findByCodeArticle(code));
	}

	@Override
	public ResponseEntity<List<CommandeClientDto>> findAll() {
		// TODO Auto-generated method stub
		return ResponseEntity.ok(commandeClientService.findAll());
	}

	@Override
	public ResponseEntity delete(Integer id) {
		commandeClientService.delete(id);
		return ResponseEntity.ok().build();
	}
	@Override
	public ResponseEntity<CommandeClientDto> updateEtatCommande(Integer idCommande, EtatCommande etatCommande) {
		return ResponseEntity.ok(commandeClientService.updateEtatCommande(idCommande,  etatCommande));
	}
	@Override
	public ResponseEntity<CommandeClientDto> updateQuantiteCommande(Integer idCommande, Integer idLigneCommande,
			BigDecimal quantite) {
		return ResponseEntity.ok(commandeClientService.updateQuantiteCommande(idCommande, idLigneCommande,quantite));
	}
	@Override
	public ResponseEntity<CommandeClientDto> updateClient(Integer idCommande, Integer idClient) {
	return	ResponseEntity.ok(commandeClientService.updateClient(idCommande, idClient));
	}

	@Override
	  public ResponseEntity<CommandeClientDto> updateArticle(Integer idCommande, Integer idLigneCommande, Integer idArticle) {
	    return ResponseEntity.ok(commandeClientService.updateArticle(idCommande, idLigneCommande, idArticle));
	  }
	@Override
	  public ResponseEntity<CommandeClientDto> deleteArticle(Integer idCommande, Integer idLigneCommande) {
	    return ResponseEntity.ok(commandeClientService.deleteArticle(idCommande, idLigneCommande));

}
	 /*@Override
	  public ResponseEntity<List<LigneCommandeClientDto>> findAllLignesCommandesClientByCommandeClientId(Integer idCommande) {
	    return ResponseEntity.ok(commandeClientService.findAllLignesCommandesClientByCommandeClientId(idCommande));
	  }*/
}

