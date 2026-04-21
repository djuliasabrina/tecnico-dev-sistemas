package com.smartpc.montadora.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.smartpc.montadora.dto.BancadaTesteDTO;
import com.smartpc.montadora.model.BancadaTeste;
import com.smartpc.montadora.model.Venda;
import com.smartpc.montadora.repository.BancadaTesteRepository;
import com.smartpc.montadora.repository.VendaRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class BancadaTesteService {

    private final BancadaTesteRepository bancadaRepository;
    private final VendaRepository vendaRepository; 

    public BancadaTesteService(BancadaTesteRepository bancadaRepository, VendaRepository vendaRepository){
        this.bancadaRepository = bancadaRepository;
        this.vendaRepository = vendaRepository;
    }

    private BancadaTesteDTO mapToDTO(BancadaTeste b) {
        Long idVendaRetorno = b.getVendaAtual() != null ? b.getVendaAtual().getId() : null;
        return new BancadaTesteDTO(
            b.getId(), 
            b.getHr_entrada(), 
            b.getHr_saida(), 
            b.getCdVendaAtual(), 
            idVendaRetorno
        );
    }

    public BancadaTesteDTO criarBancada(BancadaTesteDTO dto){
        BancadaTeste bancada = new BancadaTeste();
        
        bancada.setHr_entrada(dto.hr_entrada());
        bancada.setHr_saida(dto.hr_saida());
        bancada.setCdVendaAtual(dto.cdVendaAtual()); // SEM UNDERLINE AQUI

        if (dto.id_venda() != null) {
            Venda v = vendaRepository.findById(dto.id_venda())
                .orElseThrow(() -> new RuntimeException("Venda não encontrada!"));
            bancada.setVendaAtual(v);
        }

        return mapToDTO(bancadaRepository.save(bancada));
    }

    public List<BancadaTesteDTO> listarBancadas(){
        return bancadaRepository.findAll().stream()
            .map(this::mapToDTO)
            .toList();
    }

    public BancadaTesteDTO listarBancadaID(Long id){
        BancadaTeste b = bancadaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Bancada não encontrada!"));
        return mapToDTO(b);
    }

    public BancadaTesteDTO atualizarBancadaPut(Long id, BancadaTesteDTO dto){
        BancadaTeste bancada = bancadaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Bancada não encontrada!"));

        bancada.setHr_entrada(dto.hr_entrada());
        bancada.setHr_saida(dto.hr_saida());
        bancada.setCdVendaAtual(dto.cdVendaAtual()); // SEM UNDERLINE AQUI

        if (dto.id_venda() != null) {
            Venda v = vendaRepository.findById(dto.id_venda())
                .orElseThrow(() -> new RuntimeException("Venda não encontrada!"));
            bancada.setVendaAtual(v);
        } else {
            bancada.setVendaAtual(null);
        }

        return mapToDTO(bancadaRepository.save(bancada));
    }

    public BancadaTesteDTO atualizarBancadaPatch(Long id, BancadaTesteDTO dto){
        BancadaTeste bancada = bancadaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Bancada não encontrada!"));

        if(dto.hr_entrada() != null) bancada.setHr_entrada(dto.hr_entrada());
        if(dto.hr_saida() != null) bancada.setHr_saida(dto.hr_saida());
        if(dto.cdVendaAtual() != null) bancada.setCdVendaAtual(dto.cdVendaAtual()); // SEM UNDERLINE AQUI
        
        if (dto.id_venda() != null) {
            Venda v = vendaRepository.findById(dto.id_venda())
                .orElseThrow(() -> new RuntimeException("Venda não encontrada!"));
            bancada.setVendaAtual(v);
        }

        return mapToDTO(bancadaRepository.save(bancada));
    }

    public void excluirBancada(Long id){
        if(!bancadaRepository.existsById(id)){
            throw new EntityNotFoundException("A bancada não existe!");
        }
        bancadaRepository.deleteById(id);
    }
}