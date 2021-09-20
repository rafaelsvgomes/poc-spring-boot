package com.upti.poc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.upti.poc.model.mysql.Produto;
import com.upti.poc.model.postgres.Cliente;
import com.upti.poc.repository.mysql.ProdutoRepository;
import com.upti.poc.repository.postgres.ClienteRepository;

@Service
public class TestaPersistenciaDistribuidaService {

	@Autowired
	ProdutoRepository produtoRepository;

	@Autowired
	ClienteRepository clienteRepository;

	/**
	 * Salvando duas entidades em databases diferentes Cliente no Postgres e Produto
	 * no Mysql
	 * 
	 * @param cliente
	 * @param produto
	 */
	@Transactional
	public void salvarComTransacao(Cliente cliente, Produto produto) {
		produtoRepository.save(produto);
		clienteRepository.save(cliente);

	}

	/**
	 * Salvando duas entidades em databases diferentes Cliente no Postgres e Produto
	 * no Mysql
	 * 
	 * @param cliente
	 * @param produto
	 */
	public void salvarSemTransacao(Cliente cliente, Produto produto) {
		produtoRepository.save(produto);
		clienteRepository.save(cliente);
	}

}
