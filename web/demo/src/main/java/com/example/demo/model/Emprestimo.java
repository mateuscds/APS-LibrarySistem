package com.example.demo.model;

import java.time.LocalDate;

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
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long idEstudante;
    private long idLivro;
    private String nomeLivro;
    private String edicaoLivro;
    private boolean statusAberto;
    private LocalDate dataEmprestimo;
    private LocalDate dataTerminoEmprestimo;

    public Emprestimo(long idEstudante, long idLivro, String nomeLivro, String edicaoLivro, LocalDate dataEmprestimo, LocalDate dataTerminoEmprestimo) {
        this.idEstudante=idEstudante;
        this.idLivro=idLivro;
        this.nomeLivro = nomeLivro;
        this.edicaoLivro = edicaoLivro;
        this.dataEmprestimo=dataEmprestimo;
        this.dataTerminoEmprestimo=dataTerminoEmprestimo;
        this.statusAberto=true;
    }
}
