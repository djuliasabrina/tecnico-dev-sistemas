package com.tecdes.appprodutodb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tecdes.appprodutodb.dto.ProdutoDTO;
import com.tecdes.appprodutodb.model.Produto;
import com.tecdes.appprodutodb.repository.ProdutoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    public ProdutoDTO criarProduto(ProdutoDTO produtoDTO){
        Produto produto = new Produto();
        produto.setNome(produtoDTO.nome());
        produto.setPreco(produtoDTO.preco());
        produto.setEstoque(produtoDTO.estoque());
        Produto produtoSalvo = produtoRepository.save(produto);
        return new ProdutoDTO(produtoSalvo.getId(), produtoSalvo.getNome(), produtoSalvo.getPreco(), produto.getEstoque());

    }

    public List<ProdutoDTO> listarProdutos(){
        List<Produto> produtoEntity = produtoRepository.findAll();

        return produtoEntity.stream().map(produto -> new ProdutoDTO(produto.getId(), produto.getNome(), produto.getPreco(), produto.getEstoque())).toList();
    }

    public ProdutoDTO atualizarProdutoPut(Long id, ProdutoDTO produtoDTO){
        Produto produto = produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado!"));
    
        produto.setNome(produtoDTO.nome());
        produto.setPreco(produtoDTO.preco());
        produto.setEstoque(produtoDTO.estoque());

        produtoRepository.save(produto);

        return new ProdutoDTO(produto.getId(), produto.getNome(), produto.getPreco(), produto.getEstoque());
    }

    public ProdutoDTO atualizarProdutoPatch(Long id, ProdutoDTO produtoDTO){
        Produto produto = produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado!"));

        if(produtoDTO.nome() != null){
            produto.setNome(produtoDTO.nome());
        }
        if(produtoDTO.preco() != null){
            produto.setPreco(produtoDTO.preco());
        }
        if(produtoDTO.estoque() != null){
            produto.setEstoque(produtoDTO.estoque());
        }
        produtoRepository.save(produto);

        return new ProdutoDTO(produto.getId(), produto.getNome(), produto.getPreco(), produto.getEstoque());

    }

    public void excluirProduto(Long id){
        if(!produtoRepository.existsById(id)){
            throw new EntityNotFoundException("O produto com id " + id + " não existe!");
        }
        produtoRepository.deleteById(id);
    }

}
