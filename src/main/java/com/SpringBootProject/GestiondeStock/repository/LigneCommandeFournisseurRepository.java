package com.SpringBootProject.GestiondeStock.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SpringBootProject.GestiondeStock.model.LigneCommandeClient;
import com.SpringBootProject.GestiondeStock.model.LigneCommandeFournisseur;

public interface LigneCommandeFournisseurRepository extends JpaRepository<LigneCommandeFournisseur, Integer>{
	 List<LigneCommandeFournisseur> findAllByCommandeFournisseurId(Integer idCommande);

	  List<LigneCommandeFournisseur> findAllByArticleId(Integer idCommande);
}
