package com.SpringBootProject.GestiondeStock.services;

import java.util.List;

import com.SpringBootProject.GestiondeStock.dto.ArticleDto;
import com.SpringBootProject.GestiondeStock.dto.LigneCommandeClientDto;
import com.SpringBootProject.GestiondeStock.dto.LigneCommandeFournisseurDto;
import com.SpringBootProject.GestiondeStock.dto.LigneVenteDto;

public interface ArticlesService {
	ArticleDto save(ArticleDto dto);

	  ArticleDto findById(Integer id);

	  ArticleDto findByCodeArticle(String codeArticle);

	  List<ArticleDto> findAll();
	  void delete(Integer id);
	 

	  

	  List<LigneVenteDto> findHistoriqueVentes(Integer idArticle);

	  List<LigneCommandeClientDto> findHistoriaueCommandeClient(Integer idArticle);

	  List<LigneCommandeFournisseurDto> findHistoriqueCommandeFournisseur(Integer idArticle);

	  List<ArticleDto> findAllArticleByIdCategory(Integer idCategory);
}
