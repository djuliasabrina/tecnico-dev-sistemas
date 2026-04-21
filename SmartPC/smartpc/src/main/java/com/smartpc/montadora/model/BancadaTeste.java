package com.smartpc.montadora.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "bancada")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BancadaTeste {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime hr_entrada;
    private LocalDateTime hr_saida;

    @Column(name = "cd_venda_atual")
    private String cdVendaAtual;

    @OneToOne
    @JoinColumn(name = "id_venda", nullable = true, unique = true)
    private Venda vendaAtual;
}