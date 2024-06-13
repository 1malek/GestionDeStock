package com.SpringBootProject.GestiondeStock.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SpringBootProject.GestiondeStock.model.Article;
import com.SpringBootProject.GestiondeStock.model.CommandeClient;
import com.SpringBootProject.GestiondeStock.model.LigneCommandeClient;

public interface CommandeClientRepository extends JpaRepository<CommandeClient, Integer>{
	Optional<CommandeClient> findCommandeClientByCode(String code);
	List<CommandeClient> findAllByClientId(Integer id);
}
