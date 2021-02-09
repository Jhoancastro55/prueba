package com.prueba.accenture.dto;

import java.util.ArrayList;

import com.prueba.accenture.entity.Productos;

import lombok.Data;

@Data
public class RequestDTO {

	private String idCliente;
	private String direccion;
	private ArrayList<Productos> productos;
}
