package com.SpringBootProject.GestiondeStock.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.SpringBootProject.GestiondeStock.dto.EntrepriseDto;

public interface EntrepriseService {

	  EntrepriseDto save(EntrepriseDto dto);

	  EntrepriseDto findById(Integer id);

	  List<EntrepriseDto> findAll();

	  void delete(Integer id);
}
