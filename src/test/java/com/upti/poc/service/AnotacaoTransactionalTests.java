package com.upti.poc.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.upti.poc.model.postgres.Cliente;

@SpringBootTest
public class AnotacaoTransactionalTests {

	@Autowired
	private ClienteService clienteService;

//	@Test
	public void testarSalvarCliente() {
		clienteService.save(getCliente());
	}

	@Test
	public void testarSalvarClienteComErroNegocial() {
		try {
			Cliente cliente = getCliente();
			cliente.setNome("Nome - Teste Erro Negocial");
			clienteService.salvarClienteComErroNegocial(cliente);
		} catch (Exception e) {
			assertEquals(e.getMessage(), "Apesar da exception o Cliente foi salvo!");
		}
	}

	@Test
	public void testarSalvarClienteComErro() {
		try {
			Cliente cliente = getCliente();
			cliente.setNome("Nome - Teste RuntimeException");
			clienteService.testarSalvarClienteComRuntimeException(cliente);
		} catch (Exception e) {
			assertEquals(e.getMessage(), "O Cliente não foi salvo pois lançou erro do tipo Exception!");
		}
	}

	private Cliente getCliente() {
		Cliente cliente = new Cliente();
		cliente.setEmail("emailclienteteste@gmail.com");
		cliente.setNumCpfCnpj("6199996666");
		cliente.setNumTelefone("12345678900");
		return cliente;
	}
}
