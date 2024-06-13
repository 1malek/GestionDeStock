package com.SpringBootProject.GestiondeStock.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import com.SpringBootProject.GestiondeStock.dto.UtilisateurDto;
import com.SpringBootProject.GestiondeStock.exception.EntityNotFoundException;
import com.SpringBootProject.GestiondeStock.exception.ErrorCodes;

import com.SpringBootProject.GestiondeStock.model.Utilisateur;
import com.SpringBootProject.GestiondeStock.repository.UtilisateurRepository;
import com.SpringBootProject.GestiondeStock.services.UtilisateurService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@Service
@Slf4j

public class UtilisateurServiceImpl implements UtilisateurService {

  private final UtilisateurRepository utilisateurRepository;
 
  private final PasswordEncoder encoder;

  
  /*@Autowired
  public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository,
    PasswordEncoder encoder)
		  {
    this.utilisateurRepository = utilisateurRepository;
    this.encoder = encoder;
  }*/ 
  public Utilisateur save(Utilisateur utilisateur) {
	 //j'ai mis setMotDePasse au lieu de setPassword
  utilisateur.setPassword(encoder.encode(utilisateur.getPassword())); 
  return utilisateurRepository.save(utilisateur);
  }

  /* @Override
 public UtilisateurDto save(UtilisateurDto dto) {
   List<String> errors = UtilisateurValidator.validate(dto);
   if (!errors.isEmpty()) {
     log.error("Utilisateur is not valid {}", dto);
     throw new InvalidEntityException("L'utilisateur n'est pas valide", ErrorCodes.UTILISATEUR_NOT_VALID, errors);
   }

   if(userAlreadyExists(dto.getEmail())) {
     throw new InvalidEntityException("Un autre utilisateur avec le meme email existe deja", ErrorCodes.UTILISATEUR_ALREADY_EXISTS,
         Collections.singletonList("Un autre utilisateur avec le meme email existe deja dans la BDD"));
   }


   dto.setPassword(encoder.encode(dto.getPassword()));

   return UtilisateurDto.fromEntity(
       utilisateurRepository.save(
           UtilisateurDto.toEntity(dto)
       )
   );
 } 

 private boolean userAlreadyExists(String email) {
  Utilisateur user = utilisateurRepository.findUtilisateurByEmail(email);
   return user.isPresent();
 } */

  @Override
  public UtilisateurDto findById(Integer id) {
    if (id == null) {
      log.error("Utilisateur ID is null");
      return null;
    }
    return utilisateurRepository.findById(id)
        .map(UtilisateurDto::fromEntity)
        .orElseThrow(() -> new EntityNotFoundException(
            "Aucun utilisateur avec l'ID = " + id + " n' ete trouve dans la BDD",
            ErrorCodes.UTILISATEUR_NOT_FOUND)
        );
  }


@Override
public Utilisateur findByEmail(String email) {
  return utilisateurRepository.findUtilisateurByEmail(email)
      /*.map(UtilisateurDto::fromEntity)
      .orElseThrow(() -> new EntityNotFoundException(
      "Aucun utilisateur avec l'email = " + email + " n' ete trouve dans la BDD",
      ErrorCodes.UTILISATEUR_NOT_FOUND)
  )*/;
}
 @Override
  public List<UtilisateurDto> findAll() {
    return utilisateurRepository.findAll().stream()
        .map(UtilisateurDto::fromEntity)
        .collect(Collectors.toList());
  }

  @Override
  public void delete(Integer id) {
    if (id == null) {
      log.error("Utilisateur ID is null");
      return;
    }
    utilisateurRepository.deleteById(id);
  }

    
}
