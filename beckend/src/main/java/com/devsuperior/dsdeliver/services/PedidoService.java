package com.devsuperior.dsdeliver.services;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsdeliver.dto.PedidoDTO;
import com.devsuperior.dsdeliver.dto.ProdutoDTO;
import com.devsuperior.dsdeliver.entities.OrderStatus;
import com.devsuperior.dsdeliver.entities.Pedido;
import com.devsuperior.dsdeliver.entities.Produto;
import com.devsuperior.dsdeliver.repositories.PedidoRepository;
import com.devsuperior.dsdeliver.repositories.ProdutoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repository;
	
	@Autowired
	private ProdutoRepository produtorepository;
	
	@Transactional(readOnly = true)
	public List<PedidoDTO> findAll() {
		List<Pedido> list = repository.findPedidoswithProdutos();
		return list.stream().map(x -> new PedidoDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional
	public PedidoDTO insert(PedidoDTO dto) {
		Pedido pedido = new Pedido(null, dto.getAddress(), dto.getLatitude(), dto.getLongitude(),
				Instant.now(), OrderStatus.PENDING);
		for(ProdutoDTO p : dto.getProdutos()) {
			Produto produto = produtorepository.getOne(p.getId());
			pedido.getProdutos().add(produto);
		}
		pedido = repository.save(pedido);
		return new PedidoDTO(pedido);
	}
	
	@Transactional
	public PedidoDTO setDelivered(Long id) {
		Pedido pedido = repository.getOne(id);
		pedido.setStatus(OrderStatus.DELIVERED);
		pedido = repository.save(pedido);
		return new PedidoDTO(pedido);
	}
}
