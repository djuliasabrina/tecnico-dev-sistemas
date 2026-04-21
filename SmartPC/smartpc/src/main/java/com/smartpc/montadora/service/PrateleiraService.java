package com.smartpc.montadora.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.smartpc.montadora.dto.PrateleiraDTO;
import com.smartpc.montadora.model.Prateleira;
import com.smartpc.montadora.repository.PrateleiraRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PrateleiraService {

    private final PrateleiraRepository prateleiraRepository;

    public PrateleiraService(PrateleiraRepository prateleiraRepository){
        this.prateleiraRepository = prateleiraRepository;
    }

    @Transactional
    public PrateleiraDTO criarPrateleira(PrateleiraDTO dto) {
        validarNumeroPrateleira(dto.numero());
    
        Prateleira prateleira = Prateleira.builder()
                .numero(dto.numero())
                .build();
        
        Prateleira salva = prateleiraRepository.save(prateleira);
        return converterParaDTO(salva);
    }

    public List<PrateleiraDTO> listarPrateleiras(){
        return prateleiraRepository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }

    public PrateleiraDTO listarPrateleiraID(Long id){
        Prateleira p = prateleiraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prateleira não encontrada!"));
        return converterParaDTO(p);
    }

    @Transactional
    public PrateleiraDTO atualizarPrateleiraPut(Long id, PrateleiraDTO dto){
        validarNumeroPrateleira(dto.numero());
        Prateleira prateleira = prateleiraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prateleira não encontrada!"));

        prateleira.setNumero(dto.numero());

        Prateleira salva = prateleiraRepository.save(prateleira);
        return converterParaDTO(salva);
    }

    @Transactional
    public PrateleiraDTO atualizarPrateleiraPatch(Long id, PrateleiraDTO dto){
        Prateleira prateleira = prateleiraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prateleira não encontrada!"));

        if(dto.numero() >= 1 && dto.numero() <= 20){ 
            prateleira.setNumero(dto.numero());
        }

        Prateleira salva = prateleiraRepository.save(prateleira);
        return converterParaDTO(salva);
    }

    @Transactional
    public void excluirPrateleira(Long id){
        if(!prateleiraRepository.existsById(id)){
            throw new EntityNotFoundException("A prateleira com id " + id + " não existe!");
        }
        prateleiraRepository.deleteById(id);
    }

    // --- Métodos Auxiliares para limpar o código ---

    private void validarNumeroPrateleira(int numero) {
        if (numero < 1 || numero > 20) {
            throw new RuntimeException("Número de prateleira inválido! Escolha entre 1 e 20.");
        }
    }

    private PrateleiraDTO converterParaDTO(Prateleira p) {
        // Ajuste os parâmetros do construtor conforme o seu novo PrateleiraDTO
        return new PrateleiraDTO(p.getId(), p.getNumero());
    }
}