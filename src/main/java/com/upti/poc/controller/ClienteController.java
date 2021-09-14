package com.upti.poc.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.upti.poc.model.Cliente;
import com.upti.poc.repository.ClienteRepository;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;

	@GetMapping("/clientes")
	public ResponseEntity<List<Cliente>> getAll() {
		log.info("###### Rodando getClientes");
		List<Cliente> lista = clienteRepository.findAll();

		if (lista.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<Cliente>>(lista, HttpStatus.OK);
		}
	}

	@GetMapping("/clientes/{id}")
	public ResponseEntity<Cliente> getById(@PathVariable(value = "id") Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		if (cliente.isPresent()) {
			return new ResponseEntity<Cliente>(cliente.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/clientes")
	public ResponseEntity<Cliente> save(@RequestBody @Validated Cliente cliente) {
		return new ResponseEntity<Cliente>(clienteRepository.save(cliente), HttpStatus.CREATED);
	}

	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		Optional<Cliente> clienteAux = clienteRepository.findById(id);
		if (clienteAux.isPresent()) {
			clienteRepository.delete(clienteAux.get());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/clientes/{id}")
	public ResponseEntity<Cliente> update(@PathVariable(value = "id") Long id,
			@RequestBody @Validated Cliente cliente) {
		Optional<Cliente> clienteAux = clienteRepository.findById(id);
		if (clienteAux.isPresent()) {
			cliente.setId(id);
			return new ResponseEntity<Cliente>(clienteRepository.save(cliente), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
