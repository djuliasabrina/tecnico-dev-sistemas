package com.tecdes.appprodutodb.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tecdes.appprodutodb.dto.ProdutoDTO;
import com.tecdes.appprodutodb.service.ProdutoService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService){
        this.produtoService = produtoService;
    }


    // CREATE
    @PostMapping("/salvar")
    public ResponseEntity<ProdutoDTO> criarProduto(@RequestBody ProdutoDTO produtoDTO) {
       
        ProdutoDTO produtoCriado = produtoService.criarProduto(produtoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoCriado);
        
    }
   
    // RESTORE
    @GetMapping("/listar")
    public ResponseEntity<List<ProdutoDTO>> listarProdutos(){
        List<ProdutoDTO> produtos = produtoService.listarProdutos();

        if(produtos.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(produtos);
    }
    
    // UPDATE
    @PutMapping("put/{id}")
    public ResponseEntity<ProdutoDTO> atualizarProdutoPut(@PathVariable Long id, @RequestBody ProdutoDTO produtoDTO){
        ProdutoDTO produtoAtualizado = produtoService.atualizarProdutoPut(id, produtoDTO);

        return ResponseEntity.ok(produtoAtualizado);
    }

    // UPDATE
    @PatchMapping("patch/{id}")
    public ResponseEntity<ProdutoDTO> atualizarProdutoPatch(@PathVariable Long id, @RequestBody ProdutoDTO produtoDTO) {
       ProdutoDTO produtoAtualizado = produtoService.atualizarProdutoPatch(id, produtoDTO);
        
        return ResponseEntity.ok(produtoAtualizado);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id){

        produtoService.excluirProduto(id);

        return ResponseEntity.noContent().build();
    }

}
