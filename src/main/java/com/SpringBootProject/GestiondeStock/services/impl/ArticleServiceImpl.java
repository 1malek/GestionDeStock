package com.SpringBootProject.GestiondeStock.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.SpringBootProject.GestiondeStock.Validator.ArticleValidator;
import com.SpringBootProject.GestiondeStock.dto.ArticleDto;
import com.SpringBootProject.GestiondeStock.dto.LigneCommandeClientDto;
import com.SpringBootProject.GestiondeStock.dto.LigneCommandeFournisseurDto;
import com.SpringBootProject.GestiondeStock.dto.LigneVenteDto;
import com.SpringBootProject.GestiondeStock.exception.EntityNotFoundException;
import com.SpringBootProject.GestiondeStock.exception.ErrorCodes;
import com.SpringBootProject.GestiondeStock.exception.InvalidEntityException;
import com.SpringBootProject.GestiondeStock.model.Article;
import com.SpringBootProject.GestiondeStock.model.LigneCommandeClient;
import com.SpringBootProject.GestiondeStock.model.LigneCommandeFournisseur;
import com.SpringBootProject.GestiondeStock.model.LigneVente;
import com.SpringBootProject.GestiondeStock.repository.ArticleRepository;
import com.SpringBootProject.GestiondeStock.repository.LigneCommandeClientRepository;
import com.SpringBootProject.GestiondeStock.repository.LigneCommandeFournisseurRepository;
import com.SpringBootProject.GestiondeStock.repository.LigneVenteRepository;
import com.SpringBootProject.GestiondeStock.services.ArticlesService;


import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class ArticleServiceImpl implements ArticlesService {

	  private ArticleRepository articleRepository;
	  private LigneVenteRepository venteRepository;
	  private LigneCommandeFournisseurRepository commandeFournisseurRepository;
	  private LigneCommandeClientRepository commandeClientRepository;

	  @Autowired
	  public ArticleServiceImpl(
	      ArticleRepository articleRepository,
	      LigneVenteRepository venteRepository, LigneCommandeFournisseurRepository commandeFournisseurRepository,
	      LigneCommandeClientRepository commandeClientRepository) {
	    this.articleRepository = articleRepository;
	    this.venteRepository = venteRepository;
	    this.commandeFournisseurRepository = commandeFournisseurRepository;
	    this.commandeClientRepository = commandeClientRepository;
	  }

	  @Override
	  public ArticleDto save(ArticleDto dto) {
	    List<String> errors = ArticleValidator.validate(dto);
	    if (!errors.isEmpty()) {
	      log.error("Article is not valid {}", dto);
	      throw new InvalidEntityException("L'article n'est pas valide", ErrorCodes.ARTICLE_NOT_VALID, errors);
	    }

	    return ArticleDto.fromEntity(
	        articleRepository.save(
	            ArticleDto.toEntity(dto)
	        )
	    );
	  }

	  @Override
	  public ArticleDto findById(Integer id) {
	    if (id == null) {
	      log.error("Article ID is null");
	      return null;
	    }
	    
	    Optional<Article> article=articleRepository.findById(id);
	    ArticleDto dto=ArticleDto.fromEntity(article.get());
return Optional.of(dto).orElseThrow(()->
new EntityNotFoundException("Aucun article avec l'ID = " + id + " n' ete trouve dans la BDD",
        ErrorCodes.ARTICLE_NOT_FOUND));}
	  
	  @Override
	  public ArticleDto findByCodeArticle(String codeArticle) {
	    if (!StringUtils.hasLength(codeArticle)) {
	      log.error("Article CODE is null");
	      return null;
	    }

	    Optional<Article> article=articleRepository.findArticleByCodeArticle(codeArticle);
	    ArticleDto dto=ArticleDto.fromEntity(article.get());
     return Optional.of(ArticleDto.fromEntity(article.get())).orElseThrow(()->
     new EntityNotFoundException("Aucun article avec l'ID = " + codeArticle+ " n' ete trouve dans la BDD",
        ErrorCodes.ARTICLE_NOT_FOUND));}
	  

	  @Override
	  public List<ArticleDto> findAll() {
	    return articleRepository.findAll().stream()
	        .map(ArticleDto::fromEntity)
	        .collect(Collectors.toList());
	  }

	  @Override
	  public void delete(Integer id) {
	    if (id == null) {
	      log.error("Article ID is null");
	      return;
	    }
	   articleRepository.deleteById(id);
	  }

	@Override
	public List<LigneVenteDto> findHistoriqueVentes(Integer idArticle) {
		 return venteRepository.findAllByArticleId(idArticle).stream()
			        .map(LigneVenteDto::fromEntity)
			        .collect(Collectors.toList());
	}

	@Override
	public List<LigneCommandeClientDto> findHistoriaueCommandeClient(Integer idArticle) {
		 return null; /*commandeClientRepository.findAllByArticleId(idArticle).stream()
			        .map(LigneCommandeClientDto::fromEntity)
			        .collect(Collectors.toList());*/
	}

	@Override
	public List<LigneCommandeFournisseurDto> findHistoriqueCommandeFournisseur(Integer idArticle) {
		return commandeFournisseurRepository.findAllByArticleId(idArticle).stream()
		        .map(LigneCommandeFournisseurDto::fromEntity)
		        .collect(Collectors.toList());
	}

	@Override
	public List<ArticleDto> findAllArticleByIdCategory(Integer idCategory) {
		return articleRepository.findAllByCategoryId(idCategory).stream()
		        .map(ArticleDto::fromEntity)
		        .collect(Collectors.toList());
	}
}
