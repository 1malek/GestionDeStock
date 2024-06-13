package com.SpringBootProject.GestiondeStock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SpringBootProject.GestiondeStock.model.Article;
import com.SpringBootProject.GestiondeStock.model.Client;

public interface ClientRepository extends JpaRepository<Client, Integer>{

}
