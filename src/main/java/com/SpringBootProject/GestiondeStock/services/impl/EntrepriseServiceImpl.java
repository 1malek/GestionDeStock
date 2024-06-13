package com.SpringBootProject.GestiondeStock.services.impl;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.SpringBootProject.GestiondeStock.exception.InvalidEntityException;
import com.SpringBootProject.GestiondeStock.Validator.EntrepriseValidator;
import com.SpringBootProject.GestiondeStock.dto.EntrepriseDto;
import com.SpringBootProject.GestiondeStock.dto.RoleDto;
import com.SpringBootProject.GestiondeStock.dto.UtilisateurDto;
import com.SpringBootProject.GestiondeStock.exception.EntityNotFoundException;
import com.SpringBootProject.GestiondeStock.exception.ErrorCodes;
import com.SpringBootProject.GestiondeStock.repository.EntrepriseRepository;
import com.SpringBootProject.GestiondeStock.repository.RoleRepository;
import com.SpringBootProject.GestiondeStock.services.EntrepriseService;
import com.SpringBootProject.GestiondeStock.services.UtilisateurService;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Transactional(rollbackOn = Exception.class)
@Service
@Slf4j
public class EntrepriseServiceImpl implements EntrepriseService {

  private EntrepriseRepository entrepriseRepository;
  private UtilisateurService utilisateurService;
  private RoleRepository rolesRepository;

  @Autowired
  public EntrepriseServiceImpl(EntrepriseRepository entrepriseRepository, UtilisateurService utilisateurService,
      RoleRepository rolesRepository) {
    this.entrepriseRepository = entrepriseRepository;
    this.utilisateurService = utilisateurService;
    this.rolesRepository = rolesRepository;
  }

  @Override
  public EntrepriseDto save(EntrepriseDto dto) {
    List<String> errors = EntrepriseValidator.validate(dto);
    if (!errors.isEmpty()) {
      log.error("Entreprise is not valid {}", dto);
      throw new InvalidEntityException("L'entreprise n'est pas valide", ErrorCodes.ENTREPRISE_NOT_VALID, errors);
    }
    EntrepriseDto savedEntreprise = EntrepriseDto.fromEntity(
        entrepriseRepository.save(EntrepriseDto.toEntity(dto))
    );

   /* UtilisateurDto utilisateur = fromEntreprise(savedEntreprise);

    UtilisateurDto savedUser = utilisateurService.save(utilisateurDto);

    RoleDto rolesDto = RoleDto.builder()
        .roleName("ADMIN")
        .utilisateur(savedUser)
        .build();

    rolesRepository.save(RoleDto.toEntity(rolesDto));*/

    return  savedEntreprise;
  }
	
	@Override
	  public EntrepriseDto findById(Integer id) {
	    if (id == null) {
	      log.error("Entreprise ID is null");
	      return null;
	    }
	    return entrepriseRepository.findById(id)
	        .map(EntrepriseDto::fromEntity)
	        .orElseThrow(() -> new EntityNotFoundException(
	            "Aucune entreprise avec l'ID = " + id + " n' ete trouve dans la BDD",
	            ErrorCodes.ENTREPRISE_NOT_FOUND)
	        );
	  }
	private UtilisateurDto fromEntreprise(EntrepriseDto dto) {
	    return UtilisateurDto.builder()
	        .adresse(dto.getAdresse())
	        .nom(dto.getNom())
	        .prenom(dto.getCodeFiscal())
	        .email(dto.getEmail())
	        .password(generateRandomPassword())
	        .entreprise(dto)
	        .dateDeNaissance(Instant.now())
	        .photo(dto.getPhoto())
	        .build();
	  }
	private String generateRandomPassword() {
	    return "som3R@nd0mP@$$word";
	  }
	@Override
	  public List<EntrepriseDto> findAll() {
	    return entrepriseRepository.findAll().stream()
	        .map(EntrepriseDto::fromEntity)
	        .collect(Collectors.toList());
	  }
	 @Override
	  public void delete(Integer id) {
	    if (id == null) {
	      log.error("Entreprise ID is null");
	      return;
	    }
	    entrepriseRepository.deleteById(id);
	  }
}
