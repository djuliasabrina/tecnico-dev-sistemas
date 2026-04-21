package com.smartpc.montadora.dto;

public record ComputadorDTO(
        Long id, 
        String modeloGabinete, 
        String cor, 
        String tipo, 
        Long id_venda,       
        Long id_prateleira   
) {}