package com.SpringBootProject.GestiondeStock.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.SpringBootProject.GestiondeStock.model.Ventes;

public interface VentesRepository extends JpaRepository<Ventes , Integer>{
	 Optional<Ventes> findVentesByCode(String code);
}
