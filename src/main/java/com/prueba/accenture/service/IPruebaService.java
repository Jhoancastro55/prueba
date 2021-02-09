package com.prueba.accenture.service;


import com.prueba.accenture.dto.RequestDTO;
import com.prueba.accenture.entity.Pedido;

public interface IPruebaService {

	Pedido registrarPedido(RequestDTO dto) throws Exception;
	
	Pedido editarPedido(RequestDTO dto, Integer id);
	
	void eliminarPedido(Integer id);
}
