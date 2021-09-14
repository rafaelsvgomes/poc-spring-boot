package com.upti.poc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.upti.poc.model.Produto;
import com.upti.poc.repository.ProdutoRepository;

@SpringBootTest
class PocSpringApplicationTests {

	@Autowired
	private ProdutoRepository produtoRepository;

//	@Test
	public void testarLombokProduto() {

		List<Produto> produtos = produtoRepository.findAll();

		Produto produto = new Produto();
		produto.setId(1l);
		produto.setNome("mouse");
		produto.setValor(new BigDecimal("10.00"));
		assertEquals(produtos.get(0).toString(), produto.toString());

	}

	@Test
	public void testarSaveProduto() {
		Produto produto = new Produto();
		produto.setNome("mouse");
		produto.setValor(new BigDecimal("10.00"));

		produtoRepository.save(produto);
	}

}
