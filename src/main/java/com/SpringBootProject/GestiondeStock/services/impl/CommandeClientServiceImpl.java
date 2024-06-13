package com.SpringBootProject.GestiondeStock.services.impl;

import com.SpringBootProject.GestiondeStock.dto.ArticleDto;
import com.SpringBootProject.GestiondeStock.dto.ClientDto;
import com.SpringBootProject.GestiondeStock.dto.CommandeClientDto;
import com.SpringBootProject.GestiondeStock.dto.LigneCommandeClientDto;
import com.SpringBootProject.GestiondeStock.dto.MvtStkDto;
import com.SpringBootProject.GestiondeStock.exception.EntityNotFoundException;
import com.SpringBootProject.GestiondeStock.exception.ErrorCodes;
import com.SpringBootProject.GestiondeStock.exception.InvalidEntityException;
import com.SpringBootProject.GestiondeStock.exception.InvalidOperationException;
import com.SpringBootProject.GestiondeStock.model.Article;
import com.SpringBootProject.GestiondeStock.model.Client;
import com.SpringBootProject.GestiondeStock.model.CommandeClient;
import com.SpringBootProject.GestiondeStock.model.EtatCommande;
import com.SpringBootProject.GestiondeStock.model.LigneCommandeClient;
//import com.SpringBootProject.GestiondeStock.model.SourceMvtStk;
import com.SpringBootProject.GestiondeStock.model.TypeMvtStk;
import com.SpringBootProject.GestiondeStock.repository.ArticleRepository;
import com.SpringBootProject.GestiondeStock.repository.ClientRepository;
import com.SpringBootProject.GestiondeStock.repository.CommandeClientRepository;
import com.SpringBootProject.GestiondeStock.repository.LigneCommandeClientRepository;
import com.SpringBootProject.GestiondeStock.services.CommandeClientService;
//import com.SpringBootProject.GestiondeStock.services.MvtStkService;
import com.SpringBootProject.GestiondeStock.Validator.ArticleValidator;
import com.SpringBootProject.GestiondeStock.Validator.CommandeClientValidator;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
@Slf4j
@Service
public class CommandeClientServiceImpl implements  CommandeClientService{
	private LigneCommandeClientRepository ligneCommandeClientRepository;
private CommandeClientRepository commandeClientRepository;
private ClientRepository clientRepository;
private ArticleRepository articleRepository;
@Autowired
public CommandeClientServiceImpl(ClientRepository clientRepository, CommandeClientRepository commandeClientRepository, ArticleRepository articleRepository, LigneCommandeClientRepository ligneCommandeClientRepository) {
	this.ligneCommandeClientRepository = ligneCommandeClientRepository;
	this.clientRepository = clientRepository;
    this.commandeClientRepository = commandeClientRepository;
    this.articleRepository= articleRepository;
  }
	@Override
	public CommandeClientDto save(CommandeClientDto dto) {
// VALIDER COMMANDE(CODE,date , client!= NULL
	    List<String> errors = CommandeClientValidator.validate(dto);
// faire une exception si commande n'est pas valide
	    if (!errors.isEmpty()) {
	      log.error("Commande client n'est pas valide");
	      throw new InvalidEntityException("La commande client n'est pas valide", ErrorCodes.COMMANDE_CLIENT_NOT_VALID, errors);
	    }
	    if (dto.getId() != null && dto.isCommandeLivree()) {
	        throw new InvalidOperationException("Impossible de modifier la commande lorsqu'elle est livree", ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
	      }
//verifier si client existe dans bd
	    Optional<Client> client = clientRepository.findById(dto.getClient().getId());
	    if (client.isEmpty()) {
	      log.warn("Client with ID {} was not found in the DB", dto.getClient().getId());
	      throw new EntityNotFoundException("Aucun client avec l'ID" + dto.getClient().getId() + " n'a ete trouve dans la BDD",
	          ErrorCodes.CLIENT_NOT_FOUND);
	    }
	    List<String> articleErrors = new ArrayList<>();
// validation que dans chaque ligne de commande l article existe dans la base d 
	    if (dto.getLigneCommandeClients() != null) {
	      dto.getLigneCommandeClients().forEach(ligCmdClt -> {
	        if (ligCmdClt.getArticle() != null) {
	          Optional<Article> article = articleRepository.findById(ligCmdClt.getArticle().getId());
	          if (article.isEmpty()) {
	            articleErrors.add("L'article avec l'ID " + ligCmdClt.getArticle().getId() + " n'existe pas");
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
	    dto.setDateCommande(Instant.now());
	    CommandeClient savedCmdClt = commandeClientRepository.save(CommandeClientDto.toEntity(dto));
	    if (dto.getLigneCommandeClients() != null) {
	        dto.getLigneCommandeClients().forEach(ligCmdClt -> {
	          LigneCommandeClient ligneCommandeClient = LigneCommandeClientDto.toEntity(ligCmdClt);
	          ligneCommandeClient.setCommandeClient(savedCmdClt);
	          
	          ligneCommandeClientRepository.save(ligneCommandeClient);

	        });
	    }
	        return CommandeClientDto.fromEntity(savedCmdClt);
	}

	@Override
	public CommandeClientDto findById(Integer id) {
		if (id == null) {
		      log.error("Commande client ID is NULL");
		      return null;
		    }
		    return commandeClientRepository.findById(id)
		        .map(CommandeClientDto::fromEntity)
		        .orElseThrow(() -> new EntityNotFoundException(
		            "Aucune commande client n'a ete trouve avec l'ID " + id, ErrorCodes.COMMANDE_CLIENT_NOT_FOUND
		        ));
	}

	@Override
	public CommandeClientDto findByCodeArticle(String code) {
		if (!StringUtils.hasLength(code)) {
		      log.error("Commande client ID is NULL");
		      return null;
		    }
		 return commandeClientRepository.findCommandeClientByCode(code)
			        .map(CommandeClientDto::fromEntity)
			        .orElseThrow(() -> new EntityNotFoundException(
			            "Aucune commande client n'a ete trouve avec le CODE " + code, ErrorCodes.COMMANDE_CLIENT_NOT_FOUND
			        ));
			  }

			  @Override
			  public List<CommandeClientDto> findAll() {
			    return commandeClientRepository.findAll().stream()
			        .map(CommandeClientDto::fromEntity)
			        .collect(Collectors.toList());
			  }
			  @Override
			  public CommandeClientDto updateEtatCommande(Integer idCommande, EtatCommande etatCommande) {
				 checkIdCommande(idCommande);
			    if (!StringUtils.hasLength(String.valueOf(etatCommande))) {
			      log.error("L'etat de la commande client is NULL");
			      throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec un etat null",
			          ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
			    }
			    CommandeClientDto commandeClient = checkEtatCommande(idCommande);
			    
			   // CommandeClientDto commandeClient = checkEtatCommande(idCommande);
			   // commandeClient.setEtatCommande(etatCommande);

			   // CommandeClient savedCmdClt = commandeClientRepository.save(CommandeClientDto.toEntity(commandeClient));
			    
			    commandeClient.setEtatCommande(etatCommande);

			    return CommandeClientDto.fromEntity(commandeClientRepository.save(CommandeClientDto.toEntity(commandeClient)));
			  }
			  @Override
			  public void delete(Integer id) {
			    if (id == null) {
			      log.error("Commande client ID is NULL");
			      return;
			    }
			    
			    commandeClientRepository.deleteById(id);
			  }
			  
			  private void checkIdCommande(Integer idCommande) {
				    if (idCommande == null) {
				      log.error("Commande client ID is NULL");
				      throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec un ID null",
				          ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
				    }
				  }
			 
			  @Override
			  public CommandeClientDto updateQuantiteCommande(Integer idCommande, Integer idLigneCommande, BigDecimal quantite) {
				  checkIdCommande(idCommande);
				  checkIdLigneCommande(idLigneCommande);
			  
			    if (quantite==null|| quantite.compareTo(BigDecimal.ZERO)==0) {
				      log.error("L'id de la ligne commande client is NULL");
				      throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec un etat null",
				          ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
				    }
			    CommandeClientDto commandeClient = checkEtatCommande(idCommande);
			    
			    Optional<LigneCommandeClient> ligneCommandeClientOptional=findLigneCommandeClient(idLigneCommande);
			    
			    LigneCommandeClient ligneCommandeClient=  ligneCommandeClientOptional.get();
			    ligneCommandeClient.setQuantite(quantite);
			    ligneCommandeClientRepository.save(ligneCommandeClient);
			
			    return commandeClient;
			  }
			private Optional<LigneCommandeClient> findLigneCommandeClient(Integer idLigneCommande) {
				Optional<LigneCommandeClient>ligneCommandeClientOptional=ligneCommandeClientRepository. findById(idLigneCommande);;
			    if (ligneCommandeClientOptional.isEmpty()) {
			    	throw new InvalidOperationException("aucune ligne commande client n'a été trouvé avec l'id"+idLigneCommande,
					          ErrorCodes.COMMANDE_CLIENT_NOT_FOUND);
					    }
				return ligneCommandeClientOptional;
			}
			private void checkIdLigneCommande(Integer idLigneCommande) {
				  if (idLigneCommande==null) {
				      log.error("L'etat de la commande client is NULL");
				      throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec un etat null",
				          ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
				    }
			}
			
			
			private void checkIdArticle(Integer idArticle ,String msj) {
				  if (idArticle==null) {
				      log.error("L'etat de "+msj+" is NULL");
				      throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec un ancien"+msj+" id article",
				          ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
				    }
			}
			@Override
			public CommandeClientDto updateClient(Integer idCommande, Integer idClient) {
				checkIdCommande(idCommande);
			    if (idClient==null) {
			      log.error("L'etat de la commande client is NULL");
			      throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec un etat null",
			          ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
			    }
			    CommandeClientDto commandeClient= checkEtatCommande(idCommande);
			  
			    Optional<Client> clientOptional=clientRepository. findById(idClient);;
			    if (clientOptional.isEmpty()) {
			    	throw new EntityNotFoundException("aucune  client n'a été trouvé avec l'id"+idClient,
					          ErrorCodes.CLIENT_NOT_FOUND);
					    }  
			    commandeClient.setClient(ClientDto.fromEntity(clientOptional.get()));
return CommandeClientDto.fromEntity(commandeClientRepository.save(CommandeClientDto.toEntity(commandeClient)));
			}
			private CommandeClientDto checkEtatCommande(Integer idCommande) {
				  
					    CommandeClientDto commandeClient = findById(idCommande);
					    if (commandeClient.isCommandeLivree()) {
					      throw new InvalidOperationException("Impossible de modifier la commande lorsqu'elle est livree", ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
					    }
					   return commandeClient;
			}
			@Override
			  public CommandeClientDto updateArticle(Integer idCommande, Integer idLigneCommande, Integer idArticle) {
			    checkIdCommande(idCommande);
			    checkIdLigneCommande(idLigneCommande);
			    checkIdArticle(idArticle, "nouvel");
 CommandeClientDto commandeClient = checkEtatCommande(idCommande);
 Optional<LigneCommandeClient> ligneCommandeClient = findLigneCommandeClient(idLigneCommande);
 Optional<Article> articleOptional = articleRepository.findById(idArticle);
			    if (articleOptional.isEmpty()) {
			      throw new EntityNotFoundException(
			          "Aucune article n'a ete trouve avec l'ID " + idArticle, ErrorCodes.ARTICLE_NOT_FOUND);
			    }
List<String> errors = ArticleValidator.validate(ArticleDto.fromEntity(articleOptional.get()));
			    if (!errors.isEmpty()) {
			      throw new InvalidEntityException("Article invalid", ErrorCodes.ARTICLE_NOT_VALID, errors);
			    }

			    LigneCommandeClient ligneCommandeClientToSaved = ligneCommandeClient.get();
			    ligneCommandeClientToSaved.setArticle(articleOptional.get());
			    ligneCommandeClientRepository.save(ligneCommandeClientToSaved);

			    return commandeClient;
			  }
			@Override
			public CommandeClientDto deleteArticle(Integer idCommande, Integer idLigneCommande) {
				  checkIdCommande(idCommande);
				    checkIdLigneCommande(idLigneCommande);

				    CommandeClientDto commandeClient = checkEtatCommande(idCommande);
				    // Just to check the LigneCommandeClient and inform the client in case it is absent
				    findLigneCommandeClient(idLigneCommande);
				    ligneCommandeClientRepository.deleteById(idLigneCommande);

				    return commandeClient;
			}
			@Override
			  public List<LigneCommandeClientDto> findAllLignesCommandesClientByCommandeClientId(Integer idCommande) {
			    return ligneCommandeClientRepository.findAllByCommandeClientId(idCommande).stream()
			        .map(LigneCommandeClientDto::fromEntity)
			        .collect(Collectors.toList());
			  }
			
}
