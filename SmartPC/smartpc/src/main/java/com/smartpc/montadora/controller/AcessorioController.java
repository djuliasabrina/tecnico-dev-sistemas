package com.smartpc.montadora.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.smartpc.montadora.dto.AcessorioDTO;
import com.smartpc.montadora.service.AcessorioService;

@RestController
@RequestMapping("/acessorios")
public class AcessorioController {

    private final AcessorioService acessorioService;

    public AcessorioController(AcessorioService acessorioService) {
        this.acessorioService = acessorioService;
    }

    // CREATE
    @PostMapping("/salvar")
    public ResponseEntity<AcessorioDTO> criarAcessorio(@RequestBody AcessorioDTO dto) {
        AcessorioDTO criado = acessorioService.criarAcessorio(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    // READ ALL
    @GetMapping("/listar")
    public ResponseEntity<List<AcessorioDTO>> listarAcessorios() {
        List<AcessorioDTO> lista = acessorioService.listarAcessorios();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    // READ BY ID
    @GetMapping("listarID/{id}")
    public ResponseEntity<AcessorioDTO> listarAcessorioID(@PathVariable Long id) {
        AcessorioDTO dto = acessorioService.listarAcessorioID(id);
        return ResponseEntity.ok(dto);
    }

    // UPDATE PUT
    @PutMapping("put/{id}")
    public ResponseEntity<AcessorioDTO> atualizarAcessorioPut(@PathVariable Long id, @RequestBody AcessorioDTO dto) {
        AcessorioDTO atualizado = acessorioService.atualizarAcessorioPut(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    // UPDATE PATCH
    @PatchMapping("patch/{id}")
    public ResponseEntity<AcessorioDTO> atualizarAcessorioPatch(@PathVariable Long id, @RequestBody AcessorioDTO dto) {
        AcessorioDTO atualizado = acessorioService.atualizarAcessorioPatch(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAcessorio(@PathVariable Long id) {
        acessorioService.excluirAcessorio(id);
        return ResponseEntity.noContent().build();
    }
}