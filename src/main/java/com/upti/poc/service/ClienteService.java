package com.upti.poc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.upti.poc.exception.NegocioException;
import com.upti.poc.exception.RollbackException;
import com.upti.poc.model.postgres.Cliente;
import com.upti.poc.repository.postgres.ClienteRepository;

@Service
@Transactional
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	public Optional<Cliente> findById(Long id) {
		return clienteRepository.findById(id);
	}

	public Cliente save(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public void delete(Cliente cliente) {
		clienteRepository.delete(cliente);
	}

	// Teste da anotação @transactional
	public Cliente salvarClienteComErroNegocial(Cliente cliente) throws NegocioException {
		Cliente clienteSalvo = clienteRepository.save(cliente);

		if (clienteSalvo != null) {
			throw new NegocioException("Apesar da exception o Cliente foi salvo!");
		}
		return clienteSalvo;
	}

	public Cliente testarSalvarClienteComRuntimeException(Cliente cliente) throws RollbackException {
		Cliente clienteSalvo = clienteRepository.save(cliente);

		if (clienteSalvo != null) {
			throw new RollbackException("O Cliente não foi salvo pois lançou erro do tipo Exception!");
		}
		return clienteSalvo;
	}
}
