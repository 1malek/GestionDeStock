package com.SpringBootProject.GestiondeStock.Validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.SpringBootProject.GestiondeStock.dto.CategoryDto;
import com.SpringBootProject.GestiondeStock.dto.VentesDto;

public class VentesValidator {
	public static List<String> validate(VentesDto ventesDto) {
	    List<String> errors = new ArrayList<>();

	    if (ventesDto == null || !StringUtils.hasLength(ventesDto.getCode())) {
	      errors.add("Veuillez renseigner le code de la categorie");
	    }
	    return errors;
	  }
}
