package com.SpringBootProject.GestiondeStock.services.impl;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.SpringBootProject.GestiondeStock.Validator.ArticleValidator;
import com.SpringBootProject.GestiondeStock.Validator.CommandeClientValidator;
import com.SpringBootProject.GestiondeStock.Validator.CommandeFournisseurValidator;
import com.SpringBootProject.GestiondeStock.dto.ArticleDto;
import com.SpringBootProject.GestiondeStock.dto.CommandeClientDto;
import com.SpringBootProject.GestiondeStock.dto.CommandeFournisseurDto;
import com.SpringBootProject.GestiondeStock.dto.FournisseurDto;
import com.SpringBootProject.GestiondeStock.dto.LigneCommandeClientDto;
import com.SpringBootProject.GestiondeStock.dto.LigneCommandeFournisseurDto;
import com.SpringBootProject.GestiondeStock.exception.EntityNotFoundException;
import com.SpringBootProject.GestiondeStock.exception.ErrorCodes;
import com.SpringBootProject.GestiondeStock.exception.InvalidEntityException;
import com.SpringBootProject.GestiondeStock.exception.InvalidOperationException;
import com.SpringBootProject.GestiondeStock.model.Article;
import com.SpringBootProject.GestiondeStock.model.Client;
import com.SpringBootProject.GestiondeStock.model.CommandeClient;
import com.SpringBootProject.GestiondeStock.model.CommandeFournisseur;
import com.SpringBootProject.GestiondeStock.model.EtatCommande;
import com.SpringBootProject.GestiondeStock.model.Fournisseur;
import com.SpringBootProject.GestiondeStock.model.LigneCommandeClient;
import com.SpringBootProject.GestiondeStock.model.LigneCommandeFournisseur;
import com.SpringBootProject.GestiondeStock.repository.ArticleRepository;
import com.SpringBootProject.GestiondeStock.repository.ClientRepository;
import com.SpringBootProject.GestiondeStock.repository.CommandeClientRepository;
import com.SpringBootProject.GestiondeStock.repository.CommandeFournisseurRepository;
import com.SpringBootProject.GestiondeStock.repository.FournisseurRepository;
import com.SpringBootProject.GestiondeStock.repository.LigneCommandeClientRepository;
import com.SpringBootProject.GestiondeStock.repository.LigneCommandeFournisseurRepository;
import com.SpringBootProject.GestiondeStock.services.CommandeFournisseurService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class CommandeFournisseurServiceImpl implements CommandeFournisseurService{
	
	private LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository; 
	private CommandeFournisseurRepository commandeFournisseurRepository;
	private FournisseurRepository fournisseurRepository;
	private ArticleRepository articleRepository;
	@Autowired
	public CommandeFournisseurServiceImpl(FournisseurRepository fournisseurRepository, CommandeFournisseurRepository commandeFournisseurRepository, ArticleRepository articleRepository, LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository) {
		this.ligneCommandeFournisseurRepository = ligneCommandeFournisseurRepository;
		this.fournisseurRepository = fournisseurRepository;
	    this.commandeFournisseurRepository = commandeFournisseurRepository;
	    this.articleRepository= articleRepository;
	  }
		@Override
		public CommandeFournisseurDto save(CommandeFournisseurDto dto) {
	// VALIDER COMMANDE(CODE,date , client!= NULL
		    List<String> errors = CommandeFournisseurValidator.validate(dto);
	// faire une exception si commande n'est pas valide
		    if (!errors.isEmpty()) {
		      log.error("Commande Fournisseur n'est pas valide");
		      throw new InvalidEntityException("La commande fournisseur n'est pas valide", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_VALID, errors);
		    }

	//verifier si client existe dans bd
		    Optional<Fournisseur> fournisseur = fournisseurRepository.findById(dto.getFournisseur().getId());
		    if (fournisseur.isEmpty()) {
		      log.warn("Fournisseur with ID {} was not found in the DB", dto.getFournisseur().getId());
		      throw new EntityNotFoundException("Aucun client avec l'ID" + dto.getFournisseur().getId() + " n'a ete trouve dans la BDD",
		          ErrorCodes.FOURNISSEUR_NOT_FOUND);
		    }
		    List<String> articleErrors = new ArrayList<>();
	// validation que dans chaque ligne de commande l article existe dans la base d 
		    if (dto.getLigneCommandeFournisseurs() != null) {
		      dto.getLigneCommandeFournisseurs().forEach(ligCmdFrs -> {
		        if (ligCmdFrs.getArticle() != null) {
		          Optional<Article> article = articleRepository.findById(ligCmdFrs.getArticle().getId());
		          if (article.isEmpty()) {
		            articleErrors.add("L'article avec l'ID " + ligCmdFrs.getArticle().getId() + " n'existe pas");
		          }
		        } else {
		          articleErrors.add("Impossible d'enregister une commande avec un aticle NULL");
		        }
		      });
		    }

		    if (!articleErrors.isEmpty()) {
		      log.warn("");
		      throw new InvalidEntityException("Article n'existe pas dans la BDD", ErrorCodes.ARTICLE_NOT_FOUND, articleErrors);
		    }
		    // enregistrer cmmde clt
		 
		    CommandeFournisseur savedCmdFrs = commandeFournisseurRepository.save(CommandeFournisseurDto.toEntity(dto));
		    if (dto.getLigneCommandeFournisseurs() != null) {
		        dto.getLigneCommandeFournisseurs().forEach(ligCmdFrs -> {
		          LigneCommandeFournisseur ligneCommandeFournisseur = LigneCommandeFournisseurDto.toEntity(ligCmdFrs);
		          ligneCommandeFournisseur.setCommandeFournisseur(savedCmdFrs);
		          
		          ligneCommandeFournisseurRepository.save(ligneCommandeFournisseur);

		        });
		    }
		        return CommandeFournisseurDto.fromEntity(savedCmdFrs);
		}

		@Override
		public CommandeFournisseurDto findById(Integer id) {
			if (id == null) {
			      log.error("Commande Fournisseur ID is NULL");
			      return null;
			    }
			    return commandeFournisseurRepository.findById(id)
			        .map(CommandeFournisseurDto::fromEntity)
			        .orElseThrow(() -> new EntityNotFoundException(
			            "Aucune commande client n'a ete trouve avec l'ID " + id, ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND
			        ));
		}

		@Override
		public CommandeFournisseurDto findByCode(String code) {
			if (!StringUtils.hasLength(code)) {
			      log.error("Commande Fournisseur ID is NULL");
			      return null;
			    }
			 return commandeFournisseurRepository.findCommandeFournisseurByCode(code)
				        .map(CommandeFournisseurDto::fromEntity)
				        .orElseThrow(() -> new EntityNotFoundException(
				            "Aucune commande Fournisseur n'a ete trouve avec le CODE " + code, ErrorCodes.COMMANDE_CLIENT_NOT_FOUND
				        ));
				  }

				  @Override
				  public List<CommandeFournisseurDto> findAll() {
				    return commandeFournisseurRepository.findAll().stream()
				        .map(CommandeFournisseurDto::fromEntity)
				        .collect(Collectors.toList());
				  }

				  @Override
				  public void delete(Integer id) {
				    if (id == null) {
				      log.error("Commande Fournisseur ID is NULL");
				      return;
				    }
				    
				    commandeFournisseurRepository.deleteById(id);
				  }
				@Override
				public CommandeFournisseurDto updateEtatCommande(Integer idCommande, EtatCommande etatCommande) {
					checkIdCommande(idCommande);
				    if (!StringUtils.hasLength(String.valueOf(etatCommande))) {
				      log.error("L'etat de la commande fournisseur is NULL");
				      throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec un etat null",
				          ErrorCodes.COMMANDE_FOURNISSEUR_NON_MODIFIABLE);
				    }
				    CommandeFournisseurDto commandeFournisseur = checkEtatCommande(idCommande);
				    commandeFournisseur.setEtatCommande(etatCommande);

				    CommandeFournisseur savedCommande = commandeFournisseurRepository.save(CommandeFournisseurDto.toEntity(commandeFournisseur));
				    if (commandeFournisseur.isCommandeLivree()) {
				     // updateMvtStk(idCommande);
				    }
				    return CommandeFournisseurDto.fromEntity(savedCommande);
				}
				@Override
				public CommandeFournisseurDto updateQuantiteCommande(Integer idCommande, Integer idLigneCommande,
						BigDecimal quantite) {
					checkIdCommande(idCommande);
				    checkIdLigneCommande(idLigneCommande);

				    if (quantite == null || quantite.compareTo(BigDecimal.ZERO) == 0) {
				      log.error("L'ID de la ligne commande is NULL");
				      throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec une quantite null ou ZERO",
				          ErrorCodes.COMMANDE_FOURNISSEUR_NON_MODIFIABLE);
				    }

				    CommandeFournisseurDto commandeFournisseur = checkEtatCommande(idCommande);
				    Optional<LigneCommandeFournisseur> ligneCommandeFournisseurOptional = findLigneCommandeFournisseur(idLigneCommande);

				    LigneCommandeFournisseur ligneCommandeFounisseur = ligneCommandeFournisseurOptional.get();
				    ligneCommandeFounisseur.setQuantite(quantite);
				    ligneCommandeFournisseurRepository.save(ligneCommandeFounisseur);

				    return commandeFournisseur;
				}
				private Optional<LigneCommandeFournisseur> findLigneCommandeFournisseur(Integer idLigneCommande) {
				    Optional<LigneCommandeFournisseur> ligneCommandeFournisseurOptional = ligneCommandeFournisseurRepository.findById(idLigneCommande);
				    if (ligneCommandeFournisseurOptional.isEmpty()) {
				      throw new EntityNotFoundException(
				          "Aucune ligne commande fournisseur n'a ete trouve avec l'ID " + idLigneCommande, ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND);
				    }
				    return ligneCommandeFournisseurOptional;
				  }
				@Override
				public CommandeFournisseurDto updateFournisseur(Integer idCommande, Integer idFournisseur) {
					checkIdCommande(idCommande);
				    if (idFournisseur == null) {
				      log.error("L'ID du fournisseur is NULL");
				      throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec un ID fournisseur null",
				          ErrorCodes.COMMANDE_FOURNISSEUR_NON_MODIFIABLE);
				    }
				    CommandeFournisseurDto commandeFournisseur = checkEtatCommande(idCommande);
				    Optional<Fournisseur> fournisseurOptional = fournisseurRepository.findById(idFournisseur);
				    if (fournisseurOptional.isEmpty()) {
				      throw new EntityNotFoundException(
				          "Aucun fournisseur n'a ete trouve avec l'ID " + idFournisseur, ErrorCodes.FOURNISSEUR_NOT_FOUND);
				    }
				    commandeFournisseur.setFournisseur(FournisseurDto.fromEntity(fournisseurOptional.get()));

				    return CommandeFournisseurDto.fromEntity(
				        commandeFournisseurRepository.save(CommandeFournisseurDto.toEntity(commandeFournisseur))
				    );
				}
				@Override
				public CommandeFournisseurDto updateArticle(Integer idCommande, Integer idLigneCommande,
						Integer idArticle) {
					checkIdCommande(idCommande);
				    checkIdLigneCommande(idLigneCommande);
				    checkIdArticle(idArticle, "nouvel");

				    CommandeFournisseurDto commandeFournisseur = checkEtatCommande(idCommande);

				    Optional<LigneCommandeFournisseur> ligneCommandeFournisseur = findLigneCommandeFournisseur(idLigneCommande);

				    Optional<Article> articleOptional = articleRepository.findById(idArticle);
				    if (articleOptional.isEmpty()) {
				      throw new EntityNotFoundException(
				          "Aucune article n'a ete trouve avec l'ID " + idArticle, ErrorCodes.ARTICLE_NOT_FOUND);
				    }

				    List<String> errors = ArticleValidator.validate(ArticleDto.fromEntity(articleOptional.get()));
				    if (!errors.isEmpty()) {
				      throw new InvalidEntityException("Article invalid", ErrorCodes.ARTICLE_NOT_VALID, errors);
				    }

				    LigneCommandeFournisseur ligneCommandeFournisseurToSaved = ligneCommandeFournisseur.get();
				    ligneCommandeFournisseurToSaved.setArticle(articleOptional.get());
				    ligneCommandeFournisseurRepository.save(ligneCommandeFournisseurToSaved);

				    return commandeFournisseur;
				}
				@Override
				public CommandeFournisseurDto deleteArticle(Integer idCommande, Integer idLigneCommande) {
					checkIdCommande(idCommande);
				    checkIdLigneCommande(idLigneCommande);

				    CommandeFournisseurDto commandeFournisseur = checkEtatCommande(idCommande);
				    // Just to check the LigneCommandeFournisseur and inform the fournisseur in case it is absent
				    findLigneCommandeFournisseur(idLigneCommande);
				    ligneCommandeFournisseurRepository.deleteById(idLigneCommande);

				    return commandeFournisseur;
				}
				@Override
				  public List<LigneCommandeFournisseurDto> findAllLignesCommandesFournisseurByCommandeFournisseurId(Integer idCommande) {
				    return ligneCommandeFournisseurRepository.findAllByCommandeFournisseurId(idCommande).stream()
				        .map(LigneCommandeFournisseurDto::fromEntity)
				        .collect(Collectors.toList());
				  }
				 private CommandeFournisseurDto checkEtatCommande(Integer idCommande) {
					    CommandeFournisseurDto commandeFournisseur = findById(idCommande);
					    if (commandeFournisseur.isCommandeLivree()) {
					      throw new InvalidOperationException("Impossible de modifier la commande lorsqu'elle est livree", ErrorCodes.COMMANDE_FOURNISSEUR_NON_MODIFIABLE);
					    }
					    return commandeFournisseur;
					  }
				 private void checkIdCommande(Integer idCommande) {
					    if (idCommande == null) {
					      log.error("Commande fournisseur ID is NULL");
					      throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec un ID null",
					          ErrorCodes.COMMANDE_FOURNISSEUR_NON_MODIFIABLE);
					    }
					  }

					  private void checkIdLigneCommande(Integer idLigneCommande) {
					    if (idLigneCommande == null) {
					      log.error("L'ID de la ligne commande is NULL");
					      throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec une ligne de commande null",
					          ErrorCodes.COMMANDE_FOURNISSEUR_NON_MODIFIABLE);
					    }
					  }

					  private void checkIdArticle(Integer idArticle, String msg) {
					    if (idArticle == null) {
					      log.error("L'ID de " + msg + " is NULL");
					      throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec un " + msg + " ID article null",
					          ErrorCodes.COMMANDE_FOURNISSEUR_NON_MODIFIABLE);
					    }
	}}
