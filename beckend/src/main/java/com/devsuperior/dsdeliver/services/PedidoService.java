package com.devsuperior.dsdeliver.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsdeliver.dto.PedidoDTO;
import com.devsuperior.dsdeliver.entities.Pedido;
import com.devsuperior.dsdeliver.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repository;
	
	@Transactional(readOnly = true)
	public List<PedidoDTO> findAll() {
		List<Pedido> list = repository.findPedidoswithProdutos();
		return list.stream().map(x -> new PedidoDTO(x)).collect(Collectors.toList());
	}
}
