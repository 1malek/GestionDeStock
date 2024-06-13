package com.SpringBootProject.GestiondeStock.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SpringBootProject.GestiondeStock.model.Fournisseur;
import com.SpringBootProject.GestiondeStock.model.LigneCommandeClient;

public interface LigneCommandeClientRepository extends JpaRepository<LigneCommandeClient, Integer> {
	 List<LigneCommandeClient> findAllByCommandeClientId(Integer id);
	  List<LigneCommandeClient> findAllByArticleId(Integer id);
	  
}
