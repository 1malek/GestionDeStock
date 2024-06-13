package com.SpringBootProject.GestiondeStock.controller.api;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.SpringBootProject.GestiondeStock.dto.CommandeClientDto;
import com.SpringBootProject.GestiondeStock.dto.LigneCommandeClientDto;
import com.SpringBootProject.GestiondeStock.model.EtatCommande;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
@RequestMapping("commandesclients")
public interface CommandeClientApi {
	 @PostMapping(value = "/commandesclients/create")
	//envoyer le response(englobe une reponse) au lieux de l'objet
	ResponseEntity<CommandeClientDto> save(@RequestBody CommandeClientDto dto);
	
	 
	 @PatchMapping(value = "/commandesclients/etat/update/{idCommandeClient}/{etatCommande}")
		//envoyer le response(englobe une reponse) au lieux de l'objet
	 ResponseEntity< CommandeClientDto> updateEtatCommande(@PathVariable("idCommande")Integer idCommande, @PathVariable("EtatCommande")EtatCommande etatCommande);
	 
	 
	 @PatchMapping(value = "/commandesclients/update/article/{idCommande}/{idLigneCommande}/{idArticle}")
	 ResponseEntity<  CommandeClientDto> updateArticle(Integer idCommande, Integer idLigneCommande, Integer idArticle);
	 
	 @PatchMapping(value = "/commandesclients/update/client/{idCommande}/{idClient}")
	 ResponseEntity< CommandeClientDto> updateClient(@PathVariable("idCommande")Integer idCommande,@PathVariable("idClient") Integer idClient);
	 
	 @PatchMapping(value = "/commandesclients/quantité/update/{idCommande}/{idLigneCommande}/{quantite}")
	 ResponseEntity< CommandeClientDto>updateQuantiteCommande(@PathVariable("idCommande") Integer idCommande,@PathVariable("idLigneCommande") Integer idLigneCommande, @PathVariable("quantite") BigDecimal quantite);
	 
	 @GetMapping(value = "/commandesclients/{idCommandeClient}")
	ResponseEntity<CommandeClientDto> findById(@PathVariable Integer idCommandeClient);
	
	 @GetMapping(value = "/commandesclients/{codeCommandeClient}")
	ResponseEntity<CommandeClientDto> findByCodeArticle(@PathVariable("codeCommandeClient")String code);
	
	 @GetMapping(value = "/commandesclients/all")
	ResponseEntity<List<CommandeClientDto>> findAll();
	 
	  @GetMapping(value = "/commandesclients/lignesCommande/{idCommande}")
	  ResponseEntity<List<LigneCommandeClientDto>> findAllLignesCommandesClientByCommandeClientId(@PathVariable("idCommande") Integer idCommande);
	
	 @DeleteMapping(value = "/commandesclients/delete/{idCommandeClient}")
    ResponseEntity delete(@PathVariable("idCommandeClient")Integer id);

	 @DeleteMapping(value = "/commandesclients/delete/article/{idCommande}/{idLigneCommande}")
	ResponseEntity<CommandeClientDto> deleteArticle(@PathVariable("idCommande") Integer idCommande, @PathVariable("idLigneCommande") Integer idLigneCommande);
}
