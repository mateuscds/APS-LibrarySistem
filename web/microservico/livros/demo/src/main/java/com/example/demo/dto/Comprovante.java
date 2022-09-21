package com.example.demo.dto;

import java.time.LocalDate;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comprovante {
    private Long idEstudante;
    private Estoque estoque;
    LocalDate data;
}
