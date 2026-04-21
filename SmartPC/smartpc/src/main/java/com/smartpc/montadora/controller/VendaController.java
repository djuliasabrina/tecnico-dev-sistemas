package com.smartpc.montadora.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.smartpc.montadora.dto.VendaDTO;
import com.smartpc.montadora.service.VendaService;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    private final VendaService vendaService;

    public VendaController(VendaService vendaService) {
        this.vendaService = vendaService;
    }

    // CREATE
    @PostMapping("/salvar")
    public ResponseEntity<VendaDTO> criarVenda(@RequestBody VendaDTO dto) {
        VendaDTO criada = vendaService.criarVenda(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criada);
    }

    // READ ALL
    @GetMapping("/listar")
    public ResponseEntity<List<VendaDTO>> listarVendas() {
        List<VendaDTO> lista = vendaService.listarVendas();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    // READ BY ID
    @GetMapping("listarID/{id}")
    public ResponseEntity<VendaDTO> listarVendaID(@PathVariable Long id) {
        VendaDTO dto = vendaService.listarVendaID(id);
        return ResponseEntity.ok(dto);
    }

    // UPDATE PUT
    @PutMapping("put/{id}")
    public ResponseEntity<VendaDTO> atualizarVendaPut(@PathVariable Long id, @RequestBody VendaDTO dto) {
        VendaDTO atualizada = vendaService.atualizarVendaPut(id, dto);
        return ResponseEntity.ok(atualizada);
    }

    // UPDATE PATCH
    @PatchMapping("patch/{id}")
    public ResponseEntity<VendaDTO> atualizarVendaPatch(@PathVariable Long id, @RequestBody VendaDTO dto) {
        VendaDTO atualizada = vendaService.atualizarVendaPatch(id, dto);
        return ResponseEntity.ok(atualizada);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarVenda(@PathVariable Long id) {
        vendaService.excluirVenda(id);
        return ResponseEntity.noContent().build();
    }
}