package com.smartpc.montadora.dto;

import java.time.LocalDateTime;
import java.util.List;

public record VendaDTO(
    Long id,
    String cdVenda,
    Integer status, 
    Long idBancada,
    LocalDateTime dataVenda,
    List<ComputadorDTO> computadores
) {}