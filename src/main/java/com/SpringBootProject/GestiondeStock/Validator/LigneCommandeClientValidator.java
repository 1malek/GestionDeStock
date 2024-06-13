package com.SpringBootProject.GestiondeStock.Validator;

import java.util.ArrayList;
import java.util.List;

import com.SpringBootProject.GestiondeStock.dto.CommandeClientDto;
import com.SpringBootProject.GestiondeStock.dto.LigneCommandeClientDto;

public class LigneCommandeClientValidator {
	public static List<String> validate(LigneCommandeClientDto dto) {
		List<String> errors = new ArrayList<>(); 
		return errors;
		}
}