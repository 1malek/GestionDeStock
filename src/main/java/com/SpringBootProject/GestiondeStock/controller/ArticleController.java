package com.SpringBootProject.GestiondeStock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBootProject.GestiondeStock.controller.api.ArticleApi;
import com.SpringBootProject.GestiondeStock.dto.ArticleDto;
import com.SpringBootProject.GestiondeStock.dto.LigneCommandeClientDto;
import com.SpringBootProject.GestiondeStock.dto.LigneCommandeFournisseurDto;
import com.SpringBootProject.GestiondeStock.dto.LigneVenteDto;
import com.SpringBootProject.GestiondeStock.services.ArticlesService;
@RestController
public class ArticleController implements ArticleApi {

	 private ArticlesService articleService;

	  @Autowired
	  public ArticleController(
	      ArticlesService articleService
	  ) {
	    this.articleService = articleService;
	  }

	  @Override
	  public ArticleDto save(ArticleDto dto) {
	    return articleService.save(dto);
	  }

	  @Override
	  public ArticleDto findById(Integer id) {
	    return articleService.findById(id);
	  }

	  @Override
	  public ArticleDto findByCodeArticle(String codeArticle) {
	    return articleService.findByCodeArticle(codeArticle);
	  }

	  @Override
	  public List<ArticleDto> findAll() {
	    return articleService.findAll();
	  }

	  @Override
	  public void delete(Integer id) {
	    articleService.delete(id);
	  }

	@Override
	public List<LigneVenteDto> findHistoriqueVentes(Integer idArticle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LigneCommandeClientDto> findHistoriaueCommandeClient(Integer idArticle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LigneCommandeFournisseurDto> findHistoriqueCommandeFournisseur(Integer idArticle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ArticleDto> findAllArticleByIdCategory(Integer idCategory) {
		// TODO Auto-generated method stub
		return null;
	}
}
