package com.devsuperior.dsdeliver.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dsdeliver.dto.PedidoDTO;
import com.devsuperior.dsdeliver.services.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService service;
	
	@GetMapping
	public ResponseEntity<List<PedidoDTO>> findAll(){
		List<PedidoDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
}
