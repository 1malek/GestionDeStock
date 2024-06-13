package com.SpringBootProject.GestiondeStock.services.strategy;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.SpringBootProject.GestiondeStock.dto.ArticleDto;
import com.SpringBootProject.GestiondeStock.exception.InvalidOperationException;
import com.SpringBootProject.GestiondeStock.services.ArticlesService;
import com.SpringBootProject.GestiondeStock.services.FlickrService;
import com.flickr4java.flickr.FlickrException;
import com.SpringBootProject.GestiondeStock.exception.ErrorCodes;
import lombok.extern.slf4j.Slf4j;

@Service("articleStrategy")
@Slf4j
public class SaveArticlePhoto implements Strategy<ArticleDto> {

  private FlickrService flickrService;
  private ArticlesService articleService;

  @Autowired
  public SaveArticlePhoto(FlickrService flickrService, ArticlesService articleService) {
    this.flickrService = flickrService;
    this.articleService = articleService;
  }

  @Override
  public ArticleDto savePhoto(Integer id, InputStream photo, String titre) throws FlickrException {
    ArticleDto article = articleService.findById(id);
    String urlPhoto = flickrService.savePhoto(photo, titre);
    if (!StringUtils.hasLength(urlPhoto)) {
      throw new InvalidOperationException("Erreur lors de l'enregistrement de photo de l'article", ErrorCodes. UPDATE_PHOTO_EXCEPTION);
    }
    article.setPhoto(urlPhoto);
    return articleService.save(article);
  }
}
