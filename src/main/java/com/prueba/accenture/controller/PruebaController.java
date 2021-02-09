package com.prueba.accenture.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.accenture.dto.RequestDTO;
import com.prueba.accenture.dto.ResponseDTO;
import com.prueba.accenture.entity.Pedido;
import com.prueba.accenture.service.IPruebaService;

@RestController
@RequestMapping("/accenture")
public class PruebaController {
	
	@Autowired
	private IPruebaService service;

	@GetMapping(value = "/hello", produces = "application/json")
	public ResponseEntity<ResponseDTO> helloPrueba(){
		ResponseDTO dto = new ResponseDTO();
		dto.setMessage("Conectado con prueba Accenture");
		ResponseEntity<ResponseDTO> entity = new ResponseEntity<ResponseDTO>(dto, HttpStatus.OK);
		return entity;
	}
	
	@PostMapping(value = "/register", consumes = "application/json",produces = "application/json")
	public ResponseEntity<ResponseDTO> register(@RequestBody RequestDTO dto){
		ResponseDTO responseDTO = new ResponseDTO();
		Pedido pedido = new Pedido();
		try {
			pedido = service.registrarPedido(dto);
			responseDTO.setMessage("Pedido registrado con exito, el valor del pedido es: " + pedido.getValorTotal());
			return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.CREATED);
		} catch (Exception e) {
			responseDTO.setMessage("No se pudo registrar el pedido");
			return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
	@PutMapping(value = "/update", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ResponseDTO> update(@RequestBody RequestDTO dto, @RequestParam Integer id){
		ResponseDTO responseDTO = new ResponseDTO();
		Pedido pedido = new Pedido();
		try {
			pedido = service.editarPedido(dto, id);
			responseDTO.setMessage("Pedido editado con exito, el valor del pedido es: " + pedido.getValorTotal());
			return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
		} catch (Exception e) {
			responseDTO.setMessage("No se pudo registrar el pedido");
			return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value = "/delete", produces = "application/json")
	public ResponseEntity<ResponseDTO> delete(@RequestParam Integer id){
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			service.eliminarPedido(id);
			responseDTO.setMessage("Pedido eliminado con exito");
			return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
		} catch (Exception e) {
			responseDTO.setMessage("No se pudo eliminar el pedido");
			return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		
	}
}
