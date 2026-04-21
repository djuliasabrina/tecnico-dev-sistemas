package com.smartpc.montadora.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.smartpc.montadora.dto.PrateleiraDTO;
import com.smartpc.montadora.service.PrateleiraService;

@RestController
@RequestMapping("/prateleiras")
public class PrateleiraController {

    private final PrateleiraService prateleiraService;

    public PrateleiraController(PrateleiraService prateleiraService) {
        this.prateleiraService = prateleiraService;
    }

    // CREATE
    @PostMapping("/salvar")
    public ResponseEntity<PrateleiraDTO> criarPrateleira(@RequestBody PrateleiraDTO dto) {
        PrateleiraDTO criada = prateleiraService.criarPrateleira(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criada);
    }

    // READ ALL
    @GetMapping("/listar")
    public ResponseEntity<List<PrateleiraDTO>> listarPrateleiras() {
        List<PrateleiraDTO> lista = prateleiraService.listarPrateleiras();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    // READ BY ID
    @GetMapping("listarID/{id}")
    public ResponseEntity<PrateleiraDTO> listarPrateleiraID(@PathVariable Long id) {
        PrateleiraDTO dto = prateleiraService.listarPrateleiraID(id);
        return ResponseEntity.ok(dto);
    }

    // UPDATE PUT
    @PutMapping("put/{id}")
    public ResponseEntity<PrateleiraDTO> atualizarPrateleiraPut(@PathVariable Long id, @RequestBody PrateleiraDTO dto) {
        PrateleiraDTO atualizada = prateleiraService.atualizarPrateleiraPut(id, dto);
        return ResponseEntity.ok(atualizada);
    }

    // UPDATE PATCH
    @PatchMapping("patch/{id}")
    public ResponseEntity<PrateleiraDTO> atualizarPrateleiraPatch(@PathVariable Long id, @RequestBody PrateleiraDTO dto) {
        PrateleiraDTO atualizada = prateleiraService.atualizarPrateleiraPatch(id, dto);
        return ResponseEntity.ok(atualizada);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPrateleira(@PathVariable Long id) {
        prateleiraService.excluirPrateleira(id);
        return ResponseEntity.noContent().build();
    }
}