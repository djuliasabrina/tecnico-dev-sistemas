package com.smartpc.montadora.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.smartpc.montadora.dto.ComputadorDTO;
import com.smartpc.montadora.service.ComputadorService;

@RestController
@RequestMapping("/computadores")
public class ComputadorController {

    private final ComputadorService computadorService;

    public ComputadorController(ComputadorService computadorService) {
        this.computadorService = computadorService;
    }

    // CREATE
    @PostMapping("/salvar")
    public ResponseEntity<ComputadorDTO> criarComputador(@RequestBody ComputadorDTO dto) {
        ComputadorDTO criado = computadorService.criarComputador(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    // READ ALL
    @GetMapping("/listar")
    public ResponseEntity<List<ComputadorDTO>> listarComputadores() {
        List<ComputadorDTO> lista = computadorService.listarComputadores();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    // READ BY ID
    @GetMapping("listarID/{id}")
    public ResponseEntity<ComputadorDTO> listarComputadorID(@PathVariable Long id) {
        ComputadorDTO dto = computadorService.listarComputadorID(id);
        return ResponseEntity.ok(dto);
    }

    // UPDATE PUT
    @PutMapping("put/{id}")
    public ResponseEntity<ComputadorDTO> atualizarComputadorPut(@PathVariable Long id, @RequestBody ComputadorDTO dto) {
        ComputadorDTO atualizado = computadorService.atualizarComputadorPut(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    // UPDATE PATCH
    @PatchMapping("patch/{id}")
    public ResponseEntity<ComputadorDTO> atualizarComputadorPatch(@PathVariable Long id, @RequestBody ComputadorDTO dto) {
        ComputadorDTO atualizado = computadorService.atualizarComputadorPatch(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarComputador(@PathVariable Long id) {
        computadorService.excluirComputador(id);
        return ResponseEntity.noContent().build();
    }
}