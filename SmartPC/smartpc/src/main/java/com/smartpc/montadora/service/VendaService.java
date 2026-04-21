package com.smartpc.montadora.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.smartpc.montadora.dto.VendaDTO;
import com.smartpc.montadora.dto.ComputadorDTO;
import com.smartpc.montadora.model.Venda;
import com.smartpc.montadora.model.BancadaTeste;
import com.smartpc.montadora.repository.VendaRepository;
import com.smartpc.montadora.repository.BancadaTesteRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class VendaService {

    private final VendaRepository vendaRepository;
    private final BancadaTesteRepository bancadaRepository;

    public VendaService(VendaRepository vendaRepository, BancadaTesteRepository bancadaRepository) {
        this.vendaRepository = vendaRepository;
        this.bancadaRepository = bancadaRepository;
    }

    // Método auxiliar para converter Model em DTO
    private VendaDTO mapToDTO(Venda v) {
        Long idBancada = bancadaRepository.findByCdVendaAtual(v.getCdVenda())
                            .map(BancadaTeste::getId)
                            .orElse(null);

        List<ComputadorDTO> compDTOs = (v.getComputadores() != null) ? v.getComputadores().stream()
            .map(c -> new ComputadorDTO(
                c.getId(), c.getModeloGabinete(), c.getCor(), c.getTipo(), 
                v.getId(), (c.getPrateleira() != null ? c.getPrateleira().getId() : null)
            )).toList() : List.of();

        return new VendaDTO(
            v.getId(), v.getCdVenda(), v.getStatus(), 
            idBancada, v.getDataVenda(), compDTOs
        );
    }

    public VendaDTO criarVenda(VendaDTO dto) {
        Venda venda = new Venda();
        venda.setCdVenda(dto.cdVenda());
        venda.setStatus(dto.status());
        // A data_venda geralmente é setada automaticamente via @PrePersist na Model ou LocalDateTime.now()
        return mapToDTO(vendaRepository.save(venda));
    }

    public List<VendaDTO> listarVendas() {
        return vendaRepository.findAll().stream().map(this::mapToDTO).toList();
    }

    // MÉTODO QUE FALTAVA PARA O CONTROLLER
    public VendaDTO listarVendaID(Long id) {
        Venda venda = vendaRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Venda com ID " + id + " não encontrada!"));
        return mapToDTO(venda);
    }

    public VendaDTO atualizarVendaPut(Long id, VendaDTO dto) {
        Venda venda = vendaRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Venda não encontrada!"));
        
        venda.setCdVenda(dto.cdVenda());
        venda.setStatus(dto.status());
        
        return mapToDTO(vendaRepository.save(venda));
    }

    // MÉTODO PARA ATUALIZAÇÃO PARCIAL (PATCH)
    public VendaDTO atualizarVendaPatch(Long id, VendaDTO dto) {
        Venda venda = vendaRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Venda não encontrada!"));
    
        // Agora o Java aceita essas verificações perfeitamente
        if (dto.cdVenda() != null) {
            venda.setCdVenda(dto.cdVenda());
        }
        
        if (dto.status() != null) {
            venda.setStatus(dto.status());
        }
    
        // Se você quiser permitir atualizar a data manualmente via Patch:
        if (dto.dataVenda() != null) {
            venda.setDataVenda(dto.dataVenda());
        }
    
        return mapToDTO(vendaRepository.save(venda));
    }
    public void excluirVenda(Long id) {
        if (!vendaRepository.existsById(id)) {
            throw new EntityNotFoundException("Venda não encontrada!");
        }
        vendaRepository.deleteById(id);
    }
}