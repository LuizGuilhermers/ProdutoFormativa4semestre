package com.produtoFormativa.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.produtoFormativa.entities.Produto;
import com.produtoFormativa.repository.ProdutoRepository;
import com.produtoFormativa.service.ProdutoService;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
	class ProdutoServiceTeste {

	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ProdutoRepository ProdutoRepository;
	
	@BeforeEach
	void setUp() {
		ProdutoRepository.deleteAll();
	}
	
	@DisplayName("Testando salvar Produto")
	@Test
	void testSalvarProduto() {
		Produto Produto = new Produto(null, "Julia Maria", "Garrafa", 5.00);
		
		Produto resultado = produtoService.salvarProduto(Produto);
		
		assertNotNull(resultado);
		assertEquals("Julia Maria", resultado.getNome());
		assertTrue(resultado.getId()>0);
	}
	@DisplayName("Testando listar todos Produto")
	@Test
	void testListarTodos() {
		Produto Produto1 = new Produto(null, "Julia Maria", "Garrafa", 5.00);
		Produto Produto2 = new Produto(null, "Fernando", "Termica", 20.00);
		
		produtoService.salvarProduto(Produto1);
		produtoService.salvarProduto(Produto2);
		
		List<Produto> resultado = produtoService.listarTodos();
		
		assertNotNull(resultado);
		assertEquals(2, resultado.size());
	}
	@DisplayName("Testando buscar Produto por ID")
	@Test
	void testBuscarPorId() {
		Produto Produto = new Produto(null, "Julia Maria", "Garrafa",5.00);
		
		Produto salvo = produtoService.salvarProduto(Produto);
		Optional<Produto>resultado = produtoService.buscarPorId(salvo.getId());
		
		assertTrue(resultado.isPresent());
		assertEquals("Julia Maria", resultado.get().getNome());
	}
	@DisplayName("Testando buscar Produto por ID")
	@Test
	void testAtualizarProduto() {
		Produto Produto = new Produto(null, "Julia Maria", "Garrafa", 5.00);
		Produto salvo = produtoService.salvarProduto(Produto);
		
		salvo.setNome("Leonardo");
		salvo.setDescricao("Xicara");
		
		Produto atualizado = produtoService.atualizarProduto(salvo);
		
		assertNotNull(atualizado);
		assertEquals("Leonardo", atualizado.getNome());
		assertEquals("Xicara", atualizado.getDescricao());
	}
	@DisplayName("Testando deletar Produto")
	@Test
	void testDeletarProduto() {
		Produto Produto = new Produto(null, "Julia Maria", "Garrafa", 5.00);
		Produto salvo = produtoService.salvarProduto(Produto);
		
		produtoService.deletarProduto(salvo.getId());
		
		Optional<Produto> resultado = produtoService.buscarPorId(salvo.getId());
		
		assertTrue(resultado.isEmpty());
	}
}
