package com.smartpc.montadora.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.smartpc.montadora.dto.AcessorioDTO;
import com.smartpc.montadora.model.Acessorio;
import com.smartpc.montadora.model.Computador;
import com.smartpc.montadora.repository.AcessorioRepository;
import com.smartpc.montadora.repository.ComputadorRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class AcessorioService {

    private final AcessorioRepository acessorioRepository;
    private final ComputadorRepository computadorRepository;

    public AcessorioService(AcessorioRepository acessorioRepository, ComputadorRepository computadorRepository){
        this.acessorioRepository = acessorioRepository;
        this.computadorRepository = computadorRepository;
    }

    // CREATE
    public AcessorioDTO criarAcessorio(AcessorioDTO dto){
        Acessorio acessorio = new Acessorio();
        // Ajustado para o nome exato da sua Model e DTO
        acessorio.setTipo_acessorio(dto.tipo_acessorio()); 
        
        if (dto.id_computador() != null) {
            Computador pc = computadorRepository.findById(dto.id_computador())
                .orElseThrow(() -> new RuntimeException("Computador não encontrado!"));
            acessorio.setComputador(pc);
        }

        Acessorio salvo = acessorioRepository.save(acessorio);
        return mapToDTO(salvo);
    }

    // READ ALL
    public List<AcessorioDTO> listarAcessorios(){
        return acessorioRepository.findAll().stream()
                .map(this::mapToDTO)
                .toList();
    }

    // READ BY ID
    public AcessorioDTO listarAcessorioID(Long id){
        Acessorio a = acessorioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Acessório não encontrado!"));
        return mapToDTO(a);
    }

    // UPDATE PUT
    public AcessorioDTO atualizarAcessorioPut(Long id, AcessorioDTO dto){
        Acessorio a = acessorioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Acessório não encontrado!"));
        
        a.setTipo_acessorio(dto.tipo_acessorio());
        
        if (dto.id_computador() != null) {
            Computador pc = computadorRepository.findById(dto.id_computador()).orElseThrow();
            a.setComputador(pc);
        } else {
            a.setComputador(null);
        }

        return mapToDTO(acessorioRepository.save(a));
    }

    // UPDATE PATCH
    public AcessorioDTO atualizarAcessorioPatch(Long id, AcessorioDTO dto){
        Acessorio a = acessorioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Acessório não encontrado!"));
        
        // Como tipo_acessorio é int, verificamos se o valor enviado é válido (ex: > 0)
        if(dto.tipo_acessorio() > 0) {
            a.setTipo_acessorio(dto.tipo_acessorio());
        }
        
        if (dto.id_computador() != null) {
            Computador pc = computadorRepository.findById(dto.id_computador()).orElseThrow();
            a.setComputador(pc);
        }
        
        return mapToDTO(acessorioRepository.save(a));
    }

    // DELETE
    public void excluirAcessorio(Long id){
        if(!acessorioRepository.existsById(id)) {
            throw new EntityNotFoundException("Acessório não existe!");
        }
        acessorioRepository.deleteById(id);
    }

    // Mapeamento para DTO (Centralizado para evitar erros)
    private AcessorioDTO mapToDTO(Acessorio a) {
        Long idComp = (a.getComputador() != null) ? a.getComputador().getId() : null;
        // Certifique-se que seu Record/DTO aceite: (Long, int, Long)
        return new AcessorioDTO(a.getId(), a.getTipo_acessorio(), idComp);
    }
}