package com.freitas.clientecep.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class EnderecoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cep;       // Adicionado para armazenar o CEP
    private String logradouro;
    private String bairro;
    private String localidade; // Cidade
    private String uf;         // Estado
}

