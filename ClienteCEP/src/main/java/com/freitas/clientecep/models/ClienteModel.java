package com.freitas.clientecep.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ClienteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cep;

    @OneToOne(cascade = CascadeType.ALL)
    private EnderecoModel endereco;
}
