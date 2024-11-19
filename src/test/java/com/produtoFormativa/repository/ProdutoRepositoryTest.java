package com.produtoFormativa.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.produtoFormativa.entities.Produto;


@DataJpaTest
    class ProdutoRepositoryTest {

	@Autowired
	private ProdutoRepository produtoRepository;

	@DisplayName("Testando Save")
	@Test
	void testSalvarRepository() {
		Produto Produto1 = new Produto(null, "Julia", "Garrafa", 5.00);

		Produto saveProduto = produtoRepository.save(Produto1);

		assertNotNull(saveProduto);
		assertTrue(saveProduto.getId() > 0);
	}

	@DisplayName("Testando Get para todos Produtos")
	@Test
	void testGetAllRepository() {
		Produto Produto1 = new Produto(null, "Julia", "Garrafa", 5.00);
		Produto Produto2 = new Produto(null, "Julioo", "Squeeze", 10.0);

		produtoRepository.save(Produto1);
		produtoRepository.save(Produto2);

		List<Produto> ProdutoList = produtoRepository.findAll();

		assertNotNull(ProdutoList);
		assertEquals(2, ProdutoList.size());
	}

	@DisplayName("Testando Get By Id")
	@Test
	void testGetById() {
		Produto Produto2 = new Produto(null, "Julioo", "Squeeze", 10.0);

		produtoRepository.save(Produto2);

		Produto saveProduto = produtoRepository.findById(Produto2.getId()).get();

		assertNotNull(saveProduto);
		assertEquals(Produto2.getId(), saveProduto.getId());
	}

	@DisplayName("Testando o Update")
	@Test
	void TestUpdateProduto() {
		Produto Produto1 = new Produto(null, "Julia", "Garrafa", 5.00);
		produtoRepository.save(Produto1);
		
		Produto saveProduto = produtoRepository.findById(Produto1.getId()).get();
		
		Produto1.setNome("Leo");
		Produto1.setDescricao("Xicara");

		Produto updateProduto = produtoRepository.save(saveProduto);
		
		assertNotNull(updateProduto);
		assertEquals("Leo", updateProduto.getNome());
		assertEquals("Xicara", updateProduto.getDescricao());
	}

	@DisplayName("Testando o delete")
	@Test
	void TestDeleteProduto() {
		Produto Produto1 = new Produto(null, "Julia", "Garrafa", 5.00);
		produtoRepository.save(Produto1);

		produtoRepository.deleteById(Produto1.getId());

		Optional<Produto> ProdutoOptional = produtoRepository.findById(Produto1.getId());

		assertTrue(ProdutoOptional.isEmpty());
	}

}
