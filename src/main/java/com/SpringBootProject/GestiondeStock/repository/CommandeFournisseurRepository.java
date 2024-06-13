package com.SpringBootProject.GestiondeStock.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SpringBootProject.GestiondeStock.model.CommandeClient;
import com.SpringBootProject.GestiondeStock.model.CommandeFournisseur;

public interface CommandeFournisseurRepository  extends JpaRepository<CommandeFournisseur, Integer>{
	Optional<CommandeFournisseur> findCommandeFournisseurByCode(String code);
}
