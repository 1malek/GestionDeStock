package com.SpringBootProject.GestiondeStock.Validator;
import org.springframework.util.StringUtils;
import java.util.List;
import java.util.ArrayList;
import com.SpringBootProject.GestiondeStock.dto.CategoryDto;

public class CategoryValidator {
	public static List<String> validate(CategoryDto categoryDto) {
	    List<String> errors = new ArrayList<>();

	    if (categoryDto == null || !StringUtils.hasLength(categoryDto.getCode())) {
	      errors.add("Veuillez renseigner le code de la categorie");
	    }
	    return errors;
	  }
}
