package com.prueba.accenture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba.accenture.entity.Productos;

@Repository
public interface IProductosRepository extends JpaRepository<Productos, Integer>{

	Productos findByNombreProducto(String nombreProducto);
}
