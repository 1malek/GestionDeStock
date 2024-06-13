package com.SpringBootProject.GestiondeStock.controller.api;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.SpringBootProject.GestiondeStock.dto.UtilisateurDto;
import com.SpringBootProject.GestiondeStock.model.Utilisateur;

@RequestMapping("utilisateurs")
public interface UtilisateurApi {

 @PostMapping("utilisateurs/create")
 public Utilisateur save(@RequestBody Utilisateur utilisateur);
 
  //@PostMapping("utilisateurs/update/password")
  //UtilisateurDto changerMotDePasse(@RequestBody ChangerMotDePasseUtilisateurDto dto);

  @GetMapping("utilisateurs/{idUtilisateur}")
  UtilisateurDto findById(@PathVariable("idUtilisateur") Integer id);

  @GetMapping("utilisateurs/find/{email}")
Utilisateur findByEmail(@PathVariable("email") String email);

  @GetMapping("utilisateurs/all")
  public List<UtilisateurDto> findAll();

  @DeleteMapping("utilisateurs/delete/{idUtilisateur}")
 void delete(@PathVariable("idUtilisateur") Integer id);
}
