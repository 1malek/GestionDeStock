package com.SpringBootProject.GestiondeStock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SpringBootProject.GestiondeStock.model.Entreprise;
import com.SpringBootProject.GestiondeStock.model.Fournisseur;

public interface FournisseurRepository extends JpaRepository<Fournisseur, Integer>{

}
