package com.SpringBootProject.GestiondeStock.services.strategy;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.SpringBootProject.GestiondeStock.dto.ArticleDto;
import com.SpringBootProject.GestiondeStock.dto.UtilisateurDto;
import com.SpringBootProject.GestiondeStock.exception.InvalidOperationException;
import com.SpringBootProject.GestiondeStock.services.ArticlesService;
import com.SpringBootProject.GestiondeStock.services.FlickrService;
import com.SpringBootProject.GestiondeStock.services.UtilisateurService;
import com.flickr4java.flickr.FlickrException;
import com.SpringBootProject.GestiondeStock.exception.ErrorCodes;
import lombok.extern.slf4j.Slf4j;
@Service("utilisateurStrategy")
@Slf4j
public class SaveUtilisateurPhoto {
	/*
  private FlickrService flickrService;
  private UtilisateurService utilisateurService;

  @Autowired
  public SaveUtilisateurPhoto(FlickrService flickrService, UtilisateurService utilisateurService) {
    this.flickrService = flickrService;
    this.utilisateurService = utilisateurService;
  }

  @Override
  public UtilisateurDto savePhoto(Integer id, InputStream photo, String titre) throws FlickrException {
    UtilisateurDto utilisateur = utilisateurService.findById(id);
    String urlPhoto = flickrService.savePhoto(photo, titre);
    if (!StringUtils.hasLength(urlPhoto)) {
      throw new InvalidOperationException("Erreur lors de l'enregistrement de photo de l'utilisateur", ErrorCodes.UPDATE_PHOTO_EXCEPTION);
    }
    utilisateur.setPhoto(urlPhoto);
    return utilisateurService.save(utilisateur);
  } */ }