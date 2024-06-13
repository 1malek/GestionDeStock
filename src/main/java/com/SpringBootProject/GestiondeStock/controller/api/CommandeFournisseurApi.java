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
import com.SpringBootProject.GestiondeStock.dto.CommandeFournisseurDto;
import com.SpringBootProject.GestiondeStock.dto.LigneCommandeClientDto;
import com.SpringBootProject.GestiondeStock.dto.LigneCommandeFournisseurDto;
import com.SpringBootProject.GestiondeStock.model.EtatCommande;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
@RequestMapping("CommandeFournisseurApi")
public interface CommandeFournisseurApi {
	@PostMapping(value = "/commandesfournisseurs/create")
	ResponseEntity<CommandeFournisseurDto> save(@RequestBody CommandeFournisseurDto dto);

	 
	@GetMapping(value = "/commandesfournisseurs/{idCommandeFournisseur}")
	ResponseEntity< CommandeFournisseurDto> findById(@PathVariable Integer idCommandeFournisseur);

	
	@GetMapping(value = "/commandesfournisseurs/all")
	ResponseEntity<List<CommandeFournisseurDto> >findAll();

	  
	@DeleteMapping(value = "/commandesfournisseurs/delete/{idCommandeFournisseur}")  
	ResponseEntity delete(@PathVariable(" idCommandeFournisseur") Integer id);
	
	@PatchMapping(value = "/commandesfournisseurs/etat/update/{idCommandeFournisseur}/{etatCommande}")
	//envoyer le response(englobe une reponse) au lieux de l'objet
	 CommandeFournisseurDto updateEtatCommande(@PathVariable("idCommande") Integer idCommande, @PathVariable("etatCommande") EtatCommande etatCommande);
 
 
 @PatchMapping(value = "/commandesfournisseurs/update/article/{idCommande}/{idLigneCommande}/{idArticle}")
  CommandeFournisseurDto updateArticle(Integer idCommande, Integer idLigneCommande, Integer idArticle);
 
 @PatchMapping(value = "/commandesfournisseurs/update/client/{idCommande}/{idFournisseur}")
 CommandeFournisseurDto updateFournisseur(@PathVariable("idCommande") Integer idCommande, @PathVariable("idFournisseur") Integer idFournisseur);
 
 @PatchMapping(value = "/commandesfournisseurs/quantité/update/{idCommande}/{idLigneCommande}/{quantite}")
 CommandeFournisseurDto updateQuantiteCommande(@PathVariable("idCommande") Integer idCommande,
	     @PathVariable("idLigneCommande") Integer idLigneCommande, @PathVariable("quantite") BigDecimal quantite);
 @GetMapping(value = "/commandesfournisseurs/{codeCommandeFournisseur}")
	CommandeFournisseurDto findByCode(@PathVariable("codeCommandeFournisseur")String code);
	
 
 @GetMapping(value = "/commandesfournisseurs/lignesCommande/{idCommande}")
 List<LigneCommandeFournisseurDto> findAllLignesCommandesFournisseurByCommandeFournisseurId(@PathVariable("idCommande") Integer idCommande);
 
 @DeleteMapping(value = "/commandesfournisseurs/delete/article/{idCommande}/{idLigneCommande}")
 CommandeFournisseurDto deleteArticle(@PathVariable("idCommande") Integer idCommande, @PathVariable("idLigneCommande") Integer idLigneCommande);
 
 






 

}
