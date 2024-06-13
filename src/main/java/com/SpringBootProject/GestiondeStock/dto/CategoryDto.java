package com.SpringBootProject.GestiondeStock.dto;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;

import com.SpringBootProject.GestiondeStock.model.Article;
import com.SpringBootProject.GestiondeStock.model.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;

@Data 
@Builder
public class CategoryDto {
	private Integer id;
	private String code;
	private String designation;
	//private Integer idEntreprise;
	 @JsonIgnore
	private List<ArticleDto> articles;
	//mapping entre category vers categorydto
	public static CategoryDto fromEntity(Category category) {
	    if (category == null) {
	      return null;
	     
	    }
	    return CategoryDto.builder()
	            .id(category.getId())
	            .code(category.getCode())
	            .designation(category.getDesignation())
	            .build();
	            }
	//mapping entre categorydto vers category
	public static Category toEntity(CategoryDto categoryDto) {
	    if (categoryDto == null) {
	      return null;
	      } 
	     Category category = new Category();
	    category.setId(categoryDto.getId());
	    category.setCode(categoryDto.getCode());
	    category.setDesignation(categoryDto.getDesignation());

	     return category;
	  }
	}
