package com.example.demo.model;

import java.time.LocalDate;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comprovante {
    private Estudante estudante;
    private Estoque estoque;
    LocalDate data;
}
