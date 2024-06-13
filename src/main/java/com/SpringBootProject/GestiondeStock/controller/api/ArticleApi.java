package com.SpringBootProject.GestiondeStock.controller.api;

import com.SpringBootProject.GestiondeStock.dto.ArticleDto;
import com.SpringBootProject.GestiondeStock.dto.LigneCommandeClientDto;
import com.SpringBootProject.GestiondeStock.dto.LigneCommandeFournisseurDto;
import com.SpringBootProject.GestiondeStock.dto.LigneVenteDto;

import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;




@RequestMapping("articles")
public interface ArticleApi {
	 @PostMapping(value = "/articles/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	 @Operation(
	            description = "enregistrer un article", 
	            responses = {
	                    @ApiResponse(responseCode = "200",description = "article bien enregistré"/*,
	                            content = @Content(mediaType ="application/json", examples = {@ExampleObject( value = "{\"code\" : 200, \"Status\" : \"Ok!\", \"Message\" :\"Successfully created post!\"}"
	                                     )})*/)} )
	  ArticleDto save(@RequestBody ArticleDto dto);

	  @GetMapping(value ="/articles/{idArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
	  @Operation(
	            description = "enregistrer un article", 
	            responses = {
	                    @ApiResponse(responseCode = "200",description = "article existe dans la bd"/*,
	                            content = @Content(mediaType ="application/json", examples = {@ExampleObject( value = "{\"code\" : 200, \"Status\" : \"Ok!\", \"Message\" :\"Successfully created post!\"}"
	                                     )})*/),
	                    @ApiResponse(responseCode = "404",description = "aucun article existe"/*,
                        content = @Content(mediaType ="application/json", examples = {@ExampleObject( value = "{\"code\" : 200, \"Status\" : \"Ok!\", \"Message\" :\"Successfully created post!\"}"
                                 )})*/)
	                    } )
	  ArticleDto findById(@PathVariable("idArticle") Integer id);

	  @GetMapping(value = "/articles/filter/{codeArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
	  @Operation(
	            description = "chercher in article avec id", 
	            responses = {
	                    @ApiResponse(responseCode = "200",description = "article existe dans la bd"/*,
	                            content = @Content(mediaType ="application/json", examples = {@ExampleObject( value = "{\"code\" : 200, \"Status\" : \"Ok!\", \"Message\" :\"Successfully created post!\"}"
	                                     )})*/),
	                    @ApiResponse(responseCode = "404",description = "aucun article existe"/*,
                      content = @Content(mediaType ="application/json", examples = {@ExampleObject( value = "{\"code\" : 200, \"Status\" : \"Ok!\", \"Message\" :\"Successfully created post!\"}"
                               )})*/)
	                    } )
	  ArticleDto findByCodeArticle(@PathVariable("codeArticle") String codeArticle);

	  @GetMapping(value = "/articles/all", produces = MediaType.APPLICATION_JSON_VALUE)
	  @Operation(
	            description = "renvoi des article",
	            responses = {
	                    @ApiResponse(responseCode = "200",description = "Successfully created post!"/*,
	                            content = @Content(mediaType ="application/json", examples = {@ExampleObject( value = "{\"code\" : 200, \"Status\" : \"Ok!\", \"Message\" :\"Successfully created post!\"}"
	                                     )})*/)
	                   } )
	  List<ArticleDto> findAll();

	  
	  @DeleteMapping(value = "/articles/delete/{idArticle}")
	  @Operation(
	            description = "supprimer des articles",
	            responses = {
	                    @ApiResponse(responseCode = "200",description = "Successfully created post!"/*,
	                            content = @Content(mediaType ="application/json", examples = {@ExampleObject( value = "{\"code\" : 200, \"Status\" : \"Ok!\", \"Message\" :\"Successfully created post!\"}"
	                                     )})*/)} )
	  
	  void delete(@PathVariable("idArticle") Integer id);
	  
	  @GetMapping(value = "/articles/historique/vente/{idArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
	  List<LigneVenteDto> findHistoriqueVentes(@PathVariable("idArticle") Integer idArticle);

	  
	  @GetMapping(value = "/articles/historique/commandeclient/{idArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
	  List<LigneCommandeClientDto> findHistoriaueCommandeClient(@PathVariable("idArticle") Integer idArticle);

	  
	  @GetMapping(value = "/articles/historique/commandefournisseur/{idArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
	  List<LigneCommandeFournisseurDto> findHistoriqueCommandeFournisseur(@PathVariable("idCategory") Integer idArticle);

	  
	  @GetMapping(value = "/articles/filter/category/{idCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
	  List<ArticleDto> findAllArticleByIdCategory (@PathVariable("idCategory") Integer idCategory);
	  
}
