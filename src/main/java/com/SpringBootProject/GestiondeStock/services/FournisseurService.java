package com.SpringBootProject.GestiondeStock.services;

import java.util.List;



import com.SpringBootProject.GestiondeStock.dto.FournisseurDto;


public interface FournisseurService {

	  FournisseurDto save(FournisseurDto dto);

	  FournisseurDto findById(Integer id);

	  List<FournisseurDto> findAll();

	  void delete(Integer id);
}
