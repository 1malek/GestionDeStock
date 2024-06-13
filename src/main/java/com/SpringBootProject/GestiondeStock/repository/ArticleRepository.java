package com.SpringBootProject.GestiondeStock.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.SpringBootProject.GestiondeStock.model.Article;
public interface ArticleRepository extends JpaRepository<Article, Integer>{
	Optional<Article> findArticleByCodeArticle( String codeArticle);
	  List<Article> findAllByCategoryId(Integer idCategory);

}
