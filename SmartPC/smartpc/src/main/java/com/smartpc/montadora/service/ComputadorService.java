package com.smartpc.montadora.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.smartpc.montadora.dto.ComputadorDTO;
import com.smartpc.montadora.model.Computador;
import com.smartpc.montadora.model.Prateleira;
import com.smartpc.montadora.model.Venda;
import com.smartpc.montadora.repository.ComputadorRepository;
import com.smartpc.montadora.repository.PrateleiraRepository;
import com.smartpc.montadora.repository.VendaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ComputadorService {

    private final ComputadorRepository computadorRepository;
    private final PrateleiraRepository prateleiraRepository;
    private final VendaRepository vendaRepository;

    public ComputadorService(ComputadorRepository computadorRepository, 
                             PrateleiraRepository prateleiraRepository, 
                             VendaRepository vendaRepository) {
        this.computadorRepository = computadorRepository;
        this.prateleiraRepository = prateleiraRepository;
        this.vendaRepository = vendaRepository;
    }

    public ComputadorDTO criarComputador(ComputadorDTO dto) {
        Computador pc = new Computador();
        
        pc.setModeloGabinete(dto.modeloGabinete());
        pc.setCor(dto.cor());
        pc.setTipo(dto.tipo());
        
        if (dto.id_prateleira() != null) {
            Prateleira prateleira = prateleiraRepository.findById(dto.id_prateleira())
                .orElseThrow(() -> new RuntimeException("Prateleira não encontrada no banco!"));
            pc.setPrateleira(prateleira);
        }
            
        if (dto.id_venda() != null) {
            Venda venda = vendaRepository.findById(dto.id_venda())
                .orElseThrow(() -> new RuntimeException("Venda não encontrada no banco!"));
            pc.setVenda(venda);
        }
        
        Computador salvo = computadorRepository.save(pc);
        
        Long idVendaRetorno = (salvo.getVenda() != null) ? salvo.getVenda().getId() : null;
        Long idPrateleiraRetorno = (salvo.getPrateleira() != null) ? salvo.getPrateleira().getId() : null;
        
        return new ComputadorDTO(salvo.getId(), salvo.getModeloGabinete(), salvo.getCor(), salvo.getTipo(), 
                                 idVendaRetorno, idPrateleiraRetorno);
    }

    public List<ComputadorDTO> listarComputadores(){
        return computadorRepository.findAll().stream().map(c -> {
            Long idVendaRetorno = c.getVenda() != null ? c.getVenda().getId() : null;
            Long idPrateleiraRetorno = c.getPrateleira() != null ? c.getPrateleira().getId() : null;
            return new ComputadorDTO(c.getId(), c.getModeloGabinete(), c.getCor(), c.getTipo(), idVendaRetorno, idPrateleiraRetorno);
        }).toList();
    }

    public ComputadorDTO listarComputadorID(Long id){
        Computador c = computadorRepository.findById(id).orElseThrow(() -> new RuntimeException("Computador não encontrado!"));
        Long idVendaRetorno = c.getVenda() != null ? c.getVenda().getId() : null;
        Long idPrateleiraRetorno = c.getPrateleira() != null ? c.getPrateleira().getId() : null;
        return new ComputadorDTO(c.getId(), c.getModeloGabinete(), c.getCor(), c.getTipo(), idVendaRetorno, idPrateleiraRetorno);
    }

    public ComputadorDTO atualizarComputadorPut(Long id, ComputadorDTO dto){
        Computador pc = computadorRepository.findById(id).orElseThrow(() -> new RuntimeException("Computador não encontrado!"));

        pc.setModeloGabinete(dto.modeloGabinete());
        pc.setCor(dto.cor());
        pc.setTipo(dto.tipo());
        
        if (dto.id_prateleira() != null) {
            Prateleira prateleira = prateleiraRepository.findById(dto.id_prateleira())
                .orElseThrow(() -> new RuntimeException("Prateleira não encontrada no banco!"));
            pc.setPrateleira(prateleira);
        } else {
            pc.setPrateleira(null);
        }
            
        if (dto.id_venda() != null) {
            Venda venda = vendaRepository.findById(dto.id_venda())
                .orElseThrow(() -> new RuntimeException("Venda não encontrada no banco!"));
            pc.setVenda(venda);
        } else {
            pc.setVenda(null);
        }
        
        Computador salvo = computadorRepository.save(pc);
        
        Long idVendaRetorno = (salvo.getVenda() != null) ? salvo.getVenda().getId() : null;
        Long idPrateleiraRetorno = (salvo.getPrateleira() != null) ? salvo.getPrateleira().getId() : null;
        
        return new ComputadorDTO(salvo.getId(), salvo.getModeloGabinete(), salvo.getCor(), salvo.getTipo(), 
                                 idVendaRetorno, idPrateleiraRetorno);
    }

    public ComputadorDTO atualizarComputadorPatch(Long id, ComputadorDTO dto){
        Computador pc = computadorRepository.findById(id).orElseThrow(() -> new RuntimeException("Computador não encontrado!"));

        if(dto.modeloGabinete() != null) pc.setModeloGabinete(dto.modeloGabinete());
        if(dto.cor() != null) pc.setCor(dto.cor());
        if(dto.tipo() != null) pc.setTipo(dto.tipo());
        
        if (dto.id_prateleira() != null) {
            Prateleira prateleira = prateleiraRepository.findById(dto.id_prateleira())
                .orElseThrow(() -> new RuntimeException("Prateleira não encontrada no banco!"));
            pc.setPrateleira(prateleira);
        }
            
        if (dto.id_venda() != null) {
            Venda venda = vendaRepository.findById(dto.id_venda())
                .orElseThrow(() -> new RuntimeException("Venda não encontrada no banco!"));
            pc.setVenda(venda);
        }
        
        Computador salvo = computadorRepository.save(pc);
        
        Long idVendaRetorno = (salvo.getVenda() != null) ? salvo.getVenda().getId() : null;
        Long idPrateleiraRetorno = (salvo.getPrateleira() != null) ? salvo.getPrateleira().getId() : null;
        
        return new ComputadorDTO(salvo.getId(), salvo.getModeloGabinete(), salvo.getCor(), salvo.getTipo(), 
                                 idVendaRetorno, idPrateleiraRetorno);
    }

    public void excluirComputador(Long id){
        if(!computadorRepository.existsById(id)){
            throw new EntityNotFoundException("O computador não existe!");
        }
        computadorRepository.deleteById(id);
    }
}