package com.smartpc.montadora.dto;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;

public record BancadaTesteDTO(
    Long id,
    LocalDateTime hr_entrada,
    LocalDateTime hr_saida,
    
    @JsonProperty("cdVendaAtual") 
    String cdVendaAtual, 
    
    @JsonProperty("id_venda")
    Long id_venda
) {}