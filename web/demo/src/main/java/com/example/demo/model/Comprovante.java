package com.example.demo.model;

import java.time.LocalDate;

import javax.persistence.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comprovante {
    private Estudante estudante;
    private Livro livro;
    LocalDate data;
}
