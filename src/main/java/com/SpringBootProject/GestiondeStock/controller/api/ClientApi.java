package com.SpringBootProject.GestiondeStock.controller.api;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.SpringBootProject.GestiondeStock.dto.ClientDto;

import io.swagger.v3.oas.annotations.tags.Tag;
//@Tag("clients")
@RequestMapping("clients")
public interface ClientApi {
	 @PostMapping(value = "/clients/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	  ClientDto save(@RequestBody ClientDto dto);

	 @GetMapping(value ="/clients/{idClient}", produces = MediaType.APPLICATION_JSON_VALUE)
	  ClientDto findById(@PathVariable("idClient") Integer id);

	  
	 @GetMapping(value = "/clients/all", produces = MediaType.APPLICATION_JSON_VALUE)
	  List<ClientDto> findAll();

	/* 
	 @DeleteMapping(value = "/clients/delete/{idClient}")
	  void delete(@PathVariable("idClient") Integer id);*/
}
