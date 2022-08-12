package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.Estudante;
import com.example.demo.services.EstudanteService;


@Component
public class Facade {
    
    @Autowired
    private EstudanteService estudanteService;

    public void cadastrarEstudante(Estudante estudante) {
        estudanteService.cadastrarEstudante(estudante);
    }

    public Estudante loginEstudante(String cpf, String senha) {
        return estudanteService.loginEstudante(cpf, senha);
    }
}
