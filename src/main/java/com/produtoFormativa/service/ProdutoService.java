package com.produtoFormativa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.produtoFormativa.entities.Produto;
import com.produtoFormativa.repository.ProdutoRepository;

@Service
public class ProdutoService {
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public Produto salvarProduto(Produto Produto) {
		return produtoRepository.save(Produto);
	}
	
	public List<Produto> listarTodos(){
		return produtoRepository.findAll();
	}
	
	public Optional<Produto> buscarPorId(Long id){
		return produtoRepository.findById(id);
	}
	
	public Produto atualizarProduto(Produto Produto) {
		if(produtoRepository.existsById(Produto.getId())) {
			return produtoRepository.save(Produto);
		}else {
			throw new RuntimeException("Produto não encontrado");
		}
	}
	
	public void deletarProduto(Long id) {
		produtoRepository.deleteById(id);
	}
}
