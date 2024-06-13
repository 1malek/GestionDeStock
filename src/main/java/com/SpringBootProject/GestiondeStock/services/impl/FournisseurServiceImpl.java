package com.SpringBootProject.GestiondeStock.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SpringBootProject.GestiondeStock.Validator.FournisseurValidator;
import com.SpringBootProject.GestiondeStock.dto.FournisseurDto;
import com.SpringBootProject.GestiondeStock.exception.EntityNotFoundException;
import com.SpringBootProject.GestiondeStock.exception.ErrorCodes;
import com.SpringBootProject.GestiondeStock.exception.InvalidEntityException;
import com.SpringBootProject.GestiondeStock.model.CommandeFournisseur;
import com.SpringBootProject.GestiondeStock.repository.CommandeFournisseurRepository;
import com.SpringBootProject.GestiondeStock.repository.FournisseurRepository;
import com.SpringBootProject.GestiondeStock.services.FournisseurService;
import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class FournisseurServiceImpl implements FournisseurService {

	  private FournisseurRepository fournisseurRepository;
	  private CommandeFournisseurRepository commandeFournisseurRepository;

	  @Autowired
	  public FournisseurServiceImpl(FournisseurRepository fournisseurRepository,
	      CommandeFournisseurRepository commandeFournisseurRepository) {
	    this.fournisseurRepository = fournisseurRepository;
	    this.commandeFournisseurRepository = commandeFournisseurRepository;
	  }

	  @Override
	  public FournisseurDto save(FournisseurDto dto) {
	    List<String> errors = FournisseurValidator.validate(dto);
	    if (!errors.isEmpty()) {
	      log.error("Fournisseur is not valid {}", dto);
	      throw new InvalidEntityException("Le fournisseur n'est pas valide", ErrorCodes.FOURNISSEUR_NOT_VALID, errors);
	    }

	    return FournisseurDto.fromEntity(
	        fournisseurRepository.save(
	            FournisseurDto.toEntity(dto)
	        )
	    );
	  }

	  @Override
	  public FournisseurDto findById(Integer id) {
	    if (id == null) {
	      log.error("Fournisseur ID is null");
	      return null;
	    }
	    return fournisseurRepository.findById(id)
	        .map(FournisseurDto::fromEntity)
	        .orElseThrow(() -> new EntityNotFoundException(
	            "Aucun fournisseur avec l'ID = " + id + " n' ete trouve dans la BDD",
	            ErrorCodes.FOURNISSEUR_NOT_FOUND)
	        );
	  }

	  @Override
	  public List<FournisseurDto> findAll() {
	    return fournisseurRepository.findAll().stream()
	        .map(FournisseurDto::fromEntity)
	        .collect(Collectors.toList());
	  }

	  @Override
	  public void delete(Integer id) {
	    if (id == null) {
	      log.error("Fournisseur ID is null");
	      return;
	    }
	    /*List<CommandeFournisseur> commandeFournisseur = commandeFournisseurRepository.findAllByFournisseurId(id);
	    if (!commandeFournisseur.isEmpty()) {
	      throw new InvalidOperationException("Impossible de supprimer un fournisseur qui a deja des commandes",
	          ErrorCodes.FOURNISSEUR_ALREADY_IN_USE);
	    }*/
	    fournisseurRepository.deleteById(id);
	  }
}
