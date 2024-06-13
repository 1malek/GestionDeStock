package com.SpringBootProject.GestiondeStock.services;

import java.util.List;

import com.SpringBootProject.GestiondeStock.dto.CategoryDto;

public interface CategoryService {
	CategoryDto save(CategoryDto dto);

	  CategoryDto findById(Integer id);

	  CategoryDto findByCode(String code);

	  List<CategoryDto> findAll();

	  void delete(Integer id);
}
