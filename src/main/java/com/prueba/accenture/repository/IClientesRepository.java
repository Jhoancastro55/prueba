package com.prueba.accenture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba.accenture.entity.Clientes;

@Repository
public interface IClientesRepository extends JpaRepository<Clientes, Integer>{

	Clientes findByCedula(String cedula);
}
