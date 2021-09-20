package com.upti.poc.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.upti.poc.model.mysql.Produto;
import com.upti.poc.model.postgres.Cliente;

@SpringBootTest
public class TestaPersistenciaDistribuidaServiceTests {

	@Autowired
	TestaPersistenciaDistribuidaService service;

	@Autowired
	ProdutoService produtoService;

	@Autowired
	ClienteService clienteService;

	@Test
	public void init() {
		Optional<Produto> produto = produtoService.findById(100L);
		produto.ifPresent(produtoAux -> produtoService.delete(produtoAux));

		Optional<Cliente> cliente = clienteService.findById(100L);
		cliente.ifPresent(clienteAux -> clienteService.delete(clienteAux));
	}

	@Test
	public void testarSalvarProdutoComTransacao() {
		service.salvarComTransacao(getCliente(), getProduto());

		assertTrue(produtoService.findById(100L).isPresent());
		assertTrue(clienteService.findById(100L).isPresent());
	}

	@Test
	public void testarSalvarProdutoSemTransacao() {
		service.salvarComTransacao(getCliente(), getProduto());

		assertTrue(produtoService.findById(100L).isPresent());
		assertTrue(clienteService.findById(100L).isPresent());
	}

	private Produto getProduto() {
		Produto produto = new Produto();
		produto.setId(100L);
		produto.setNome("estabilizador 2");
		produto.setValor(new BigDecimal("100.99"));
		return produto;
	}

	private Cliente getCliente() {
		Cliente cliente = new Cliente();
		cliente.setId(100L);
		cliente.setNome("Teste");
		cliente.setEmail("emailclienteteste@gmail.com");
		cliente.setNumCpfCnpj("6199996666");
		cliente.setNumTelefone("12345678900");
		return cliente;
	}
}
