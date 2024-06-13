package com.SpringBootProject.GestiondeStock.controller.api;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.SpringBootProject.GestiondeStock.dto.VentesDto;

@RequestMapping("ventes")
public interface VentesApi {

  @PostMapping("ventes/create")
  VentesDto save(@RequestBody VentesDto dto);

  @GetMapping("ventes/{idVente}")
  VentesDto findById(@PathVariable("idVente") Integer id);

  @GetMapping("ventes/{codeVente}")
  VentesDto findByCode(@PathVariable("codeVente") String code);

  @GetMapping("ventes/all")
  List<VentesDto> findAll();

  @DeleteMapping("ventes/delete/{idVente}")
  void delete(@PathVariable("idVente") Integer id);

}
