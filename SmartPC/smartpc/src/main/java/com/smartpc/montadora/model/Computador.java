package com.smartpc.montadora.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "computador")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Computador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String modeloGabinete;
    private String cor;
    private String tipo; // "Padrão" ou "Custom"

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_venda", nullable = false)
    private Venda venda;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_prateleira", nullable = false)
    private Prateleira prateleira;

    @OneToMany(mappedBy = "computador", cascade = jakarta.persistence.CascadeType.ALL, orphanRemoval = true)
    private List<Acessorio> acessorios;
}