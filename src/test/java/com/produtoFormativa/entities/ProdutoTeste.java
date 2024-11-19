package com.produtoFormativa.entities;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.produtoFormativa.entities.Produto;

class ProdutoTeste {

	private Produto produto;
	
	@BeforeEach
	void setUp() {
		//Arrange
		produto = new Produto(1L,"Julia","Garrafa", 5.00);
	}
	
	@Test
	@DisplayName("Testando getter e setter do ID")
	void testId() {
		produto.setId(2L);
		//Assert
		assertEquals(2L,produto.getId());
	}
	
	@Test
	@DisplayName("Testando getter e setter do NOME")
	void testNome() {
		//Act
		produto.setNome("Joao Paulo");
		//Assert
		assertEquals("Joao Paulo", produto.getNome());
	}
	@Test
	@DisplayName("Testando getter e setter da DESCRIÇÃO")
	void testDescricao() {
		//Act
		produto.setDescricao("Garrafa");
		//Assert
		assertEquals("Garrafa", produto.getDescricao());
	}
	@Test
	@DisplayName("Testando getter e setter do PREÇO")
	void testPreco() {
		//Act
		produto.setPreco(10.0);
		//Assert
		assertEquals(10.0, produto.getPreco());
	}
	
	@Test
	@DisplayName("Testando todos os argumentos")
	void testConstrutor() {
		//Act
		Produto novoProduto = new Produto(3L,"Mateus","Copo", 15.0);
		//Assert
		assertAll("novoProduto",
				()-> assertEquals(3L,novoProduto.getId()),
				()-> assertEquals("Mateus",novoProduto.getNome()),
				()-> assertEquals("Copo",novoProduto.getDescricao()),
				()-> assertEquals(15.0,novoProduto.getPreco()));
	}
}
