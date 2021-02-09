package com.prueba.accenture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba.accenture.entity.Pedido;

@Repository
public interface IPedidosRepository extends JpaRepository<Pedido, Integer> {
	
}
