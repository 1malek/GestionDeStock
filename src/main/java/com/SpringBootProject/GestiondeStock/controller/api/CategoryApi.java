package com.SpringBootProject.GestiondeStock.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;


import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.SpringBootProject.GestiondeStock.dto.CategoryDto;
@RequestMapping("categories")
public interface CategoryApi {
	
	
		 @PostMapping(value = "/categories/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		 @Operation(
		            description = "enregistrer un article", 
		            responses = {
		                    @ApiResponse(responseCode = "200",description = "category bien enregistré"/*,
		                            content = @Content(mediaType ="application/json", examples = {@ExampleObject( value = "{\"code\" : 200, \"Status\" : \"Ok!\", \"Message\" :\"Successfully created post!\"}"
		                                     )})*/), @ApiResponse(responseCode = "400",description = "category n'est pas valid"/*,
		 		                            content = @Content(mediaType ="application/json", examples = {@ExampleObject( value = "{\"code\" : 200, \"Status\" : \"Ok!\", \"Message\" :\"Successfully created post!\"}"
				                                     )})*/)} )
		                    
		           
		 CategoryDto save(@RequestBody CategoryDto dto);

		  @GetMapping(value ="/categories/{idCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
		  @Operation(
		            description = "enregistrer une Category", 
		            responses = {
		                    @ApiResponse(responseCode = "200",description = "Category existe dans la bd"/*,
		                            content = @Content(mediaType ="application/json", examples = {@ExampleObject( value = "{\"code\" : 200, \"Status\" : \"Ok!\", \"Message\" :\"Successfully created post!\"}"
		                                     )})*/),
		                    @ApiResponse(responseCode = "404",description = "aucun Category existe"/*,
	                        content = @Content(mediaType ="application/json", examples = {@ExampleObject( value = "{\"code\" : 200, \"Status\" : \"Ok!\", \"Message\" :\"Successfully created post!\"}"
	                                 )})*/)
		                    } )
		  CategoryDto findById(@PathVariable("idCategory") Integer id);

		  @GetMapping(value = "/categories/{codeCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
		  @Operation(
		            description = "chercher in Category avec id", 
		            responses = {
		                    @ApiResponse(responseCode = "200",description = "Category existe dans la bd"/*,
		                            content = @Content(mediaType ="application/json", examples = {@ExampleObject( value = "{\"code\" : 200, \"Status\" : \"Ok!\", \"Message\" :\"Successfully created post!\"}"
		                                     )})*/),
		                    @ApiResponse(responseCode = "404",description = "aucun Category existe"/*,
	                      content = @Content(mediaType ="application/json", examples = {@ExampleObject( value = "{\"code\" : 200, \"Status\" : \"Ok!\", \"Message\" :\"Successfully created post!\"}"
	                               )})*/)
		                    } )
		  CategoryDto findByCodeCategory(/*@Parameter (description="accepted values  [cat1,cat2,cat3]") */@PathVariable("codeCategory") String codeCategory);
		  @GetMapping(value = "/categories/all", produces = MediaType.APPLICATION_JSON_VALUE)
		  @Operation(
		            description = "renvoi des Category",
		            responses = {
		                    @ApiResponse(responseCode = "200",description = "Successfully created post!"/*,
		                            content = @Content(mediaType ="application/json", examples = {@ExampleObject( value = "{\"code\" : 200, \"Status\" : \"Ok!\", \"Message\" :\"Successfully created post!\"}"
		                                     )})*/)
		                   } )
		  List<CategoryDto> findAll();

		  
		  @DeleteMapping(value = "/categories/delete/{idCategory}")
		  @Operation(
		            description = "supprimer des Categories",
		            responses = {
		                    @ApiResponse(responseCode = "200",description = "Successfully created post!"/*,
		                            content = @Content(mediaType ="application/json", examples = {@ExampleObject( value = "{\"code\" : 200, \"Status\" : \"Ok!\", \"Message\" :\"Successfully created post!\"}"
		                                     )})*/)} )
		  void delete(@PathVariable("idCategory") Integer id);
	}