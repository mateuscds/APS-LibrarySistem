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
public class Estoque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idLivro;
    private String nome;
    private String edicao;
    private int quantidade;
    public Estoque(Long idLivro, String nome, String edicao, int quantidade) {
        this.idLivro = idLivro;
        this.nome = nome;
        this.edicao = edicao;
        this.quantidade = quantidade;
    }
}
