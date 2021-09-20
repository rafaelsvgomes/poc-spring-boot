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

import com.upti.poc.model.mysql.Produto;
import com.upti.poc.service.ProdutoService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@GetMapping("/produtos")
	public ResponseEntity<List<Produto>> getAll() {
		log.info("###### Rodando getProdutos");
		List<Produto> lista = produtoService.findAll();

		if (lista.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<Produto>>(lista, HttpStatus.OK);
		}
	}

	@GetMapping("/produtos/{id}")
	public ResponseEntity<Produto> getById(@PathVariable(value = "id") Long id) {
		Optional<Produto> produto = produtoService.findById(id);
		if (produto.isPresent()) {
			return new ResponseEntity<Produto>(produto.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/produtos")
	public ResponseEntity<Produto> save(@RequestBody @Validated Produto produto) {
		return new ResponseEntity<Produto>(produtoService.save(produto), HttpStatus.CREATED);
	}

	@DeleteMapping("/produtos/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		Optional<Produto> produtoAux = produtoService.findById(id);
		if (produtoAux.isPresent()) {
			produtoService.delete(produtoAux.get());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/produtos/{id}")
	public ResponseEntity<Produto> update(@PathVariable(value = "id") Long id,
			@RequestBody @Validated Produto produto) {
		Optional<Produto> produtoAux = produtoService.findById(id);
		if (produtoAux.isPresent()) {
			produto.setId(id);
			return new ResponseEntity<Produto>(produtoService.save(produto), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
