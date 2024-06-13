package com.SpringBootProject.GestiondeStock.controller.api;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.SpringBootProject.GestiondeStock.dto.FournisseurDto;

@RequestMapping("fournisseur")
public interface FournisseurApi {

  @PostMapping( "fournisseur/create")
  FournisseurDto save(@RequestBody FournisseurDto dto);

  @GetMapping("fournisseur/{idFournisseur}")
  FournisseurDto findById(@PathVariable("idFournisseur") Integer id);

  @GetMapping("fournisseur/all")
  List<FournisseurDto> findAll();

  @DeleteMapping("fournisseur/delete/{idFournisseur}")
  void delete(@PathVariable("idFournisseur") Integer id);

}
