package com.SpringBootProject.GestiondeStock.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.SpringBootProject.GestiondeStock.Validator.CategoryValidator;
import com.SpringBootProject.GestiondeStock.Validator.ClientValidator;
import com.SpringBootProject.GestiondeStock.Validator.VentesValidator;
import com.SpringBootProject.GestiondeStock.dto.LigneVenteDto;
import com.SpringBootProject.GestiondeStock.dto.VentesDto;
import com.SpringBootProject.GestiondeStock.exception.EntityNotFoundException;
import com.SpringBootProject.GestiondeStock.exception.ErrorCodes;
import com.SpringBootProject.GestiondeStock.exception.InvalidEntityException;
import com.SpringBootProject.GestiondeStock.model.Article;
import com.SpringBootProject.GestiondeStock.model.LigneVente;
import com.SpringBootProject.GestiondeStock.model.Ventes;
import com.SpringBootProject.GestiondeStock.repository.ArticleRepository;
import com.SpringBootProject.GestiondeStock.repository.LigneVenteRepository;
import com.SpringBootProject.GestiondeStock.repository.VentesRepository;
import com.SpringBootProject.GestiondeStock.services.VentesService;
import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class VentesServiceImpl implements VentesService{
private ArticleRepository articleRepository;
private VentesRepository ventesRepository;
private LigneVenteRepository ligneVenteRepository;
@Autowired
public VentesServiceImpl(ArticleRepository articleRepository,VentesRepository ventesRepository, LigneVenteRepository ligneVenteRepository)
{
	this.articleRepository=articleRepository;
	this.ventesRepository=ventesRepository;
	this.ligneVenteRepository=ligneVenteRepository;
}

@Override
	public VentesDto save(VentesDto dto) {
    List<String> errors = VentesValidator.validate(dto);
    if (!errors.isEmpty()) {
      log.error("Ventes is not valid {}", dto);
      throw new InvalidEntityException("Le Ventes n'est pas valide", ErrorCodes.VENTE_NOT_VALID, errors);
    }
    List<String> articleErrors = new ArrayList<>();

    dto.getLigneVentes().forEach(ligneVenteDto -> {
      Optional<Article> article = articleRepository.findById(ligneVenteDto.getArticle().getId());
      if (article.isEmpty()) {
        articleErrors.add("Aucun article avec l'ID " + ligneVenteDto.getArticle().getId() + " n'a ete trouve dans la BDD");
      }
    });

    if (!articleErrors.isEmpty()) {
      log.error("One or more articles were not found in the DB, {}", errors);
      throw new InvalidEntityException("Un ou plusieurs articles n'ont pas ete trouve dans la BDD", ErrorCodes.VENTE_NOT_VALID, errors);
    }

    Ventes savedVentes = ventesRepository.save(VentesDto.toEntity(dto));

    dto.getLigneVentes().forEach(ligneVenteDto -> {
      LigneVente ligneVente = LigneVenteDto.toEntity(ligneVenteDto);
      ligneVente.setVente(savedVentes);
      ligneVenteRepository.save(ligneVente);
   //   updateMvtStk(ligneVente);
    });

    return VentesDto.fromEntity(savedVentes);
  }

	@Override
	public VentesDto findById(Integer id) {
		if (id == null) {
		      log.error("Ventes ID is NULL");
		      return null;
		    }
		    return ventesRepository.findById(id)
		        .map(VentesDto::fromEntity)
		        .orElseThrow(() -> new EntityNotFoundException("Aucun vente n'a ete trouve dans la BDD", ErrorCodes.VENTE_NOT_FOUND));
	}
	@Override
	public VentesDto findByCode(String code) {
		if (!StringUtils.hasLength(code)) {
		      log.error("Vente CODE is NULL");
		      return null;
		    }
		    return ventesRepository.findVentesByCode(code)
		        .map(VentesDto::fromEntity)
		        .orElseThrow(() -> new EntityNotFoundException(
		            "Aucune vente client n'a ete trouve avec le CODE " + code, ErrorCodes.VENTE_NOT_VALID
		        ));}

	@Override
	public List<VentesDto> findAll() {
		return ventesRepository.findAll().stream()
		        .map(VentesDto::fromEntity)
		        .collect(Collectors.toList());
	}

	@Override
	public void delete(Integer id) {
		if (id == null) {
		      log.error("ventes ID is null");
		      return;
		    }
		   
		ventesRepository.deleteById(id);
	}
}
