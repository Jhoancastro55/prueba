package com.prueba.accenture.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prueba.accenture.dto.RequestDTO;
import com.prueba.accenture.entity.Clientes;
import com.prueba.accenture.entity.Pedido;
import com.prueba.accenture.entity.Productos;
import com.prueba.accenture.repository.IClientesRepository;
import com.prueba.accenture.repository.IPedidosRepository;
import com.prueba.accenture.repository.IProductosRepository;

@Service
@Transactional
public class PruebaService implements IPruebaService{
	
	@Autowired
	private IPedidosRepository pedidosRepository;
	
	@Autowired
	private IProductosRepository productosRepository;
	
	@Autowired
	private IClientesRepository clientesRepository;

	@Override
	public Pedido registrarPedido(RequestDTO dto) throws Exception {
		double valorProductos = 0;
		float valorIva = 0;
		double valorDomicilio = 0;
		double valorTotal = 0;
		Timestamp timestamp = new Timestamp(System.currentTimeMillis()); 
		Pedido pedido = new Pedido();
		Clientes clientes = new Clientes();
		clientes = clientesRepository.findByCedula(dto.getIdCliente());
		if (clientes.equals(null)) {
			throw new Exception("Cliente no creado en sistema");
		}else {
			pedido.setClientes(clientes);
			pedido.setDireccionEntrega(clientes.getDireccion());
			pedido.setFechaPedido(timestamp);
			pedido.setProductos(dto.getProductos());
			valorProductos = calcularValorProductos(dto.getProductos());
			pedido.setValorpedido(valorProductos);
			valorIva = calcularIva(valorProductos);
			pedido.setIva(valorIva);
			valorDomicilio = calcularDomicilio(valorProductos, valorIva);
			pedido.setDomicilio(valorDomicilio);
			valorTotal = valorProductos + valorIva + valorDomicilio;
			pedido.setValorTotal(valorTotal);
		}
		
		return pedidosRepository.save(pedido);
	}

	@Override
	public Pedido editarPedido(RequestDTO dto, Integer id) {
		double valorProductos = 0;
		float valorIva = 0;
		double valorDomicilio = 0;
		double valorTotal = 0;
		Pedido pedis = new Pedido();
		Clientes clientes = new Clientes();
		clientes.setCedula(dto.getIdCliente());
		clientes.setDireccion(dto.getDireccion());
		pedis.setClientes(clientes);
		pedis.setProductos(dto.getProductos());
		valorProductos = calcularValorProductos(dto.getProductos());
		pedis.setValorpedido(valorProductos);
		valorIva = calcularIva(valorProductos);
		pedis.setIva(valorIva);
		valorDomicilio = calcularDomicilio(valorProductos, valorIva);
		pedis.setDomicilio(valorDomicilio);
		valorTotal = valorProductos + valorIva + valorDomicilio;
		pedis.setValorTotal(valorTotal);
		return pedidosRepository.save(pedis);
	}

	
	
	@Override
	public void eliminarPedido(Integer id) {
		pedidosRepository.deleteById(id);
		
	}
	
	public ArrayList<Productos> consultarProducto(ArrayList<Productos> pro) throws Exception {
		Productos produc = new Productos();
		ArrayList<Productos> listResponse = new ArrayList<>();
		for (Productos productos : pro) {
			produc = productosRepository.findByNombreProducto(productos.getNombreProducto());
			if (produc.getStock().equals(0)) {
				pro.remove(produc);
				throw new Exception("Producto sin stock");
			}else {
				listResponse.add(produc);
			}
		}
		return listResponse;
	}
	
	
	public double calcularValorProductos(ArrayList<Productos> pro) {
		Productos prod = new Productos();
		double valor = 0;
		for (Productos productos: pro) {
			prod = productosRepository.findByNombreProducto(productos.getNombreProducto());
			if (Objects.nonNull(prod)) {
				valor = valor + prod.getValorProducto();
			}
			 
		}
		return valor;
	}
	
	public float calcularIva(double valorPedido) {
		String valor = String.valueOf(valorPedido);
		return (float) (Float.valueOf(valor) * 0.19);
	}
	
	public double calcularDomicilio(double valorPedido, float iva) {
		double total = valorPedido + iva;
		if (total >= 100000) {
			return 0;
		}else {
			return 5000;
		}
	}

}
