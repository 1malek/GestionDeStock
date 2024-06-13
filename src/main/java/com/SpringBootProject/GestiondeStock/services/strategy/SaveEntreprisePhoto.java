package com.SpringBootProject.GestiondeStock.services.strategy;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.SpringBootProject.GestiondeStock.dto.ArticleDto;
import com.SpringBootProject.GestiondeStock.dto.EntrepriseDto;
import com.SpringBootProject.GestiondeStock.exception.InvalidOperationException;
import com.SpringBootProject.GestiondeStock.services.ArticlesService;
import com.SpringBootProject.GestiondeStock.services.EntrepriseService;
import com.SpringBootProject.GestiondeStock.services.FlickrService;
import com.flickr4java.flickr.FlickrException;
import com.SpringBootProject.GestiondeStock.exception.ErrorCodes;
import lombok.extern.slf4j.Slf4j;
@Service("entrepriseStrategy")
@Slf4j
public class SaveEntreprisePhoto implements Strategy<EntrepriseDto> {

  private FlickrService flickrService;
  private EntrepriseService entrepriseService;

  @Autowired
  public SaveEntreprisePhoto(FlickrService flickrService, EntrepriseService entrepriseService) {
    this.flickrService = flickrService;
    this.entrepriseService = entrepriseService;
  }

  @Override
  public EntrepriseDto savePhoto(Integer id, InputStream photo, String titre) throws FlickrException {
    EntrepriseDto entreprise = entrepriseService.findById(id);
    String urlPhoto = flickrService.savePhoto(photo, titre);
    if (!StringUtils.hasLength(urlPhoto)) {
      throw new InvalidOperationException("Erreur lors de l'enregistrement de photo de l'entreprise", ErrorCodes.UPDATE_PHOTO_EXCEPTION);
    }
    entreprise.setPhoto(urlPhoto);
    return entrepriseService.save(entreprise);
  }
}