package com.smartpc.montadora.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartpc.montadora.model.BancadaTeste;

public interface BancadaTesteRepository extends JpaRepository<BancadaTeste, Long >{

    Optional<BancadaTeste> findByCdVendaAtual(String cdVendaAtual);
}
