package com.smartpc.montadora.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "venda")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String cdVenda;

    @Column(nullable = true, updatable = false)
    private LocalDateTime dataVenda;

    @PrePersist
    protected void onCreate() {
        this.dataVenda = LocalDateTime.now(); // Gera a data/hora atual antes de salvar
    }

    @Column(name = "status")
    private Integer status; // 1-Aguardando, 2-Em Montagem, 3-Teste, 4-Concluído

    @OneToOne(mappedBy = "vendaAtual", cascade = jakarta.persistence.CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("vendaAtual") // Evita loop 
    private BancadaTeste bancada;

    @OneToMany(mappedBy = "venda", cascade = jakarta.persistence.CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("venda") // Evita loop infinito
    @Builder.Default
    private List<Computador> computadores = new ArrayList<>();
    
}