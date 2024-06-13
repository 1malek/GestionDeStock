package com.SpringBootProject.GestiondeStock.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SpringBootProject.GestiondeStock.model.LigneCommandeFournisseur;
import com.SpringBootProject.GestiondeStock.model.LigneVente;

public interface LigneVenteRepository extends JpaRepository<LigneVente, Integer>{
	 List<LigneVente> findAllByArticleId(Integer idArticle);

	  List<LigneVente> findAllByVenteId(Integer id);
}
