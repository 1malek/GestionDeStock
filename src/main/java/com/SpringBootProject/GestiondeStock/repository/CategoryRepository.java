package com.SpringBootProject.GestiondeStock.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SpringBootProject.GestiondeStock.model.Article;
import com.SpringBootProject.GestiondeStock.model.Category;
import com.SpringBootProject.GestiondeStock.model.CommandeFournisseur;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
	Optional<Category> findCategoryByCode(String code);
}
