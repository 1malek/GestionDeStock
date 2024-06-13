package com.SpringBootProject.GestiondeStock.dto;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import com.SpringBootProject.GestiondeStock.model.Article;
import com.SpringBootProject.GestiondeStock.model.CommandeFournisseur;
import com.SpringBootProject.GestiondeStock.model.LigneCommandeFournisseur;

import lombok.Builder;
import lombok.Data;

@Data 
@Builder
public class LigneCommandeFournisseurDto {
	private Integer id;
	private ArticleDto article;
	private CommandeFournisseurDto commandeFournisseur;
	  private BigDecimal quantite;

	  private BigDecimal prixUnitaire;
	  public static LigneCommandeFournisseurDto fromEntity(LigneCommandeFournisseur ligneCommandeFournisseur) {
		    if (ligneCommandeFournisseur == null) {
		      return null;
		    }
		    return LigneCommandeFournisseurDto.builder()
		        .id(ligneCommandeFournisseur.getId())
		      .article(ArticleDto.fromEntity(ligneCommandeFournisseur.getArticle()))
		        .quantite(ligneCommandeFournisseur.getQuantite())
		        .prixUnitaire(ligneCommandeFournisseur.getPrixUnitaire())
		       //.idEntreprise(ligneCommandeFournisseur.getIdEntreprise())
		        .build();
		  }

		  public static LigneCommandeFournisseur toEntity(LigneCommandeFournisseurDto dto) {
		    if (dto == null) {
		      return null;
		    }

		    LigneCommandeFournisseur ligneCommandeFournisseur = new LigneCommandeFournisseur();
		    ligneCommandeFournisseur.setId(dto.getId());
		    ligneCommandeFournisseur.setArticle(ArticleDto.toEntity(dto.getArticle()));
		    ligneCommandeFournisseur.setPrixUnitaire(dto.getPrixUnitaire());
		    ligneCommandeFournisseur.setQuantite(dto.getQuantite());
		   // ligneCommandeFournisseur.setIdEntreprise(dto.getIdEntreprise());
		    return ligneCommandeFournisseur;
		  }
}
