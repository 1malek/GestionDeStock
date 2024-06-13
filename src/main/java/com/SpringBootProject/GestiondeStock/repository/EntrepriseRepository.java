package com.SpringBootProject.GestiondeStock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SpringBootProject.GestiondeStock.model.CommandeFournisseur;
import com.SpringBootProject.GestiondeStock.model.Entreprise;

public interface EntrepriseRepository extends JpaRepository<Entreprise, Integer>{

}
