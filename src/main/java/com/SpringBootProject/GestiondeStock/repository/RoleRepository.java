package com.SpringBootProject.GestiondeStock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SpringBootProject.GestiondeStock.model.MvtStk;
import com.SpringBootProject.GestiondeStock.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{

}
