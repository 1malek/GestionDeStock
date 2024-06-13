package com.SpringBootProject.GestiondeStock.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SpringBootProject.GestiondeStock.Validator.ClientValidator;
import com.SpringBootProject.GestiondeStock.dto.ClientDto;
import com.SpringBootProject.GestiondeStock.exception.EntityNotFoundException;
import com.SpringBootProject.GestiondeStock.exception.ErrorCodes;
import com.SpringBootProject.GestiondeStock.exception.InvalidEntityException;
import com.SpringBootProject.GestiondeStock.exception.InvalidOperationException;
import com.SpringBootProject.GestiondeStock.model.CommandeClient;
import com.SpringBootProject.GestiondeStock.repository.ClientRepository;
import com.SpringBootProject.GestiondeStock.repository.CommandeClientRepository;
import com.SpringBootProject.GestiondeStock.services.ClientService;
import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class ClientServiceImpl implements ClientService {

	  private ClientRepository clientRepository;
	  private CommandeClientRepository commandeClientRepository;

	  @Autowired
	  public ClientServiceImpl(ClientRepository clientRepository, CommandeClientRepository commandeClientRepository) {
	    this.clientRepository = clientRepository;
	    this.commandeClientRepository = commandeClientRepository;
	  }

	  @Override
	  public ClientDto save(ClientDto dto) {
	    List<String> errors = ClientValidator.validate(dto);
	    if (!errors.isEmpty()) {
	      log.error("Client is not valid {}", dto);
	      throw new InvalidEntityException("Le client n'est pas valide", ErrorCodes.CLIENT_NOT_VALID, errors);
	    }

	    return ClientDto.fromEntity(
	        clientRepository.save(
	            ClientDto.toEntity(dto)
	        )
	    );
	  }

	  @Override
	  public ClientDto findById(Integer id) {
	    if (id == null) {
	      log.error("Client ID is null");
	      return null;
	    }
	    return clientRepository.findById(id)
	        .map(ClientDto::fromEntity)
	        .orElseThrow(() -> new EntityNotFoundException(
	            "Aucun Client avec l'ID = " + id + " n' ete trouve dans la BDD",
	            ErrorCodes.CLIENT_NOT_FOUND)
	        );
	  }

	  @Override
	  public List<ClientDto> findAll() {
	    return clientRepository.findAll().stream()
	        .map(ClientDto::fromEntity)
	        .collect(Collectors.toList());
	  }

	  @Override
	  public void delete(Integer id) {
	    if (id == null) {
	      log.error("Client ID is null");
	      return;
	    }
	    List<CommandeClient> commandeClients = commandeClientRepository.findAllByClientId(id);
	    if (!commandeClients.isEmpty()) {
	      throw new InvalidOperationException("Impossible de supprimer un client qui a deja des commande clients",
	          ErrorCodes.CLIENT_ALREADY_IN_USE);
	    }
	    clientRepository.deleteById(id);
	  }
}
