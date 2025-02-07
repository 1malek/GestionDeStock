package com.SpringBootProject.GestiondeStock.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(name="lignevente")


public class LigneVente extends AbstractEntity{
	@ManyToOne
	@JoinTable(name="idvente")
private Ventes vente;
	 @ManyToOne
	  @JoinColumn(name = "idarticle")
	  private Article article;

	  @Column(name = "quantite")
	  private BigDecimal quantite;

	  @Column(name = "prixunitaire")
	  private BigDecimal prixUnitaire;

	  @Column(name = "identreprise")
	  private Integer idEntreprise;

}
