package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Livro_estoque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String edicao;
    private int quantidade;
    private Long livroId;

    public Livro_estoque(String nome, String edicao, int quantidade, Long id) {
        this.nome = nome;
        this.edicao = edicao;
        this.quantidade = quantidade;
        this.livroId = id;

    }
}
