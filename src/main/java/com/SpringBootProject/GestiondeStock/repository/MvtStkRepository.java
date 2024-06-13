package com.SpringBootProject.GestiondeStock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SpringBootProject.GestiondeStock.model.LigneCommandeFournisseur;
import com.SpringBootProject.GestiondeStock.model.MvtStk;

public interface MvtStkRepository extends JpaRepository<MvtStk, Integer>{

}
