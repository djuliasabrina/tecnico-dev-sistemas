package com.smartpc.montadora.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.smartpc.montadora.dto.BancadaTesteDTO;
import com.smartpc.montadora.service.BancadaTesteService;

@RestController
@RequestMapping("/bancadas")
public class BancadaTesteController {

    private final BancadaTesteService bancadaTesteService;

    public BancadaTesteController(BancadaTesteService bancadaTesteService) {
        this.bancadaTesteService = bancadaTesteService;
    }

    // CREATE
    @PostMapping("/salvar")
    public ResponseEntity<BancadaTesteDTO> criarBancada(@RequestBody BancadaTesteDTO dto) {
        BancadaTesteDTO criada = bancadaTesteService.criarBancada(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criada);
    }

    // READ ALL
    @GetMapping("/listar")
    public ResponseEntity<List<BancadaTesteDTO>> listarBancadas() {
        List<BancadaTesteDTO> lista = bancadaTesteService.listarBancadas();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    // READ BY ID
    @GetMapping("listarID/{id}")
    public ResponseEntity<BancadaTesteDTO> listarBancadaID(@PathVariable Long id) {
        BancadaTesteDTO dto = bancadaTesteService.listarBancadaID(id);
        return ResponseEntity.ok(dto);
    }

    // UPDATE PUT
    @PutMapping("put/{id}")
    public ResponseEntity<BancadaTesteDTO> atualizarBancadaPut(@PathVariable Long id, @RequestBody BancadaTesteDTO dto) {
        BancadaTesteDTO atualizada = bancadaTesteService.atualizarBancadaPut(id, dto);
        return ResponseEntity.ok(atualizada);
    }

    // UPDATE PATCH
    @PatchMapping("patch/{id}")
    public ResponseEntity<BancadaTesteDTO> atualizarBancadaPatch(@PathVariable Long id, @RequestBody BancadaTesteDTO dto) {
        BancadaTesteDTO atualizada = bancadaTesteService.atualizarBancadaPatch(id, dto);
        return ResponseEntity.ok(atualizada);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarBancada(@PathVariable Long id) {
        bancadaTesteService.excluirBancada(id);
        return ResponseEntity.noContent().build();
    }
}