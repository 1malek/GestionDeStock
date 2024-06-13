package com.SpringBootProject.GestiondeStock.services;

import java.util.List;

import com.SpringBootProject.GestiondeStock.dto.ClientDto;

public interface ClientService {
	 ClientDto save(ClientDto dto);

	  ClientDto findById(Integer id);

	  List<ClientDto> findAll();

	 void delete(Integer id);
}
