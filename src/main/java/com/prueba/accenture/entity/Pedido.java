package com.prueba.accenture.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name = "PEDIDOS")
public class Pedido implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "ID_CLIENTE", nullable = false)
	private Clientes clientes;
	
	@Column(name = "direccionEntrega")
	private String direccionEntrega;
	
	@Size(max = 10000)
	@Column(name = "productos")
	private ArrayList<Productos> productos;
	
	@Column(name = "valorPedido")
	private double valorpedido;
	
	@Column(name = "iva")
	private float iva;
	
	@Column(name = "domicilio")
	private double domicilio;
	
	@Column(name = "valorTotal")
	private double valorTotal;
	
	@Column(name = "fechaPedido")
	private Timestamp fechaPedido;
	

}
