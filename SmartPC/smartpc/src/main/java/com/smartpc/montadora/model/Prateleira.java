package com.smartpc.montadora.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "prateleira")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Prateleira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "prateleira", cascade = CascadeType.ALL)
    private List<Computador> computadores;

    @Column(nullable = false)
    private int numero; // 1 á 20

}
