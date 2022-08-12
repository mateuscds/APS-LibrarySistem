package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.collection.EstudanteCollection;
import com.example.demo.model.Estudante;

@Service
public class EstudanteService {
    @Autowired
    private EstudanteCollection estudanteCollection;

    public boolean consultarEstudante(Estudante estudante) {
        return true;
    }

    public void cadastrarEstudante(Estudante estudante) {
        estudanteCollection.cadastrarEstudante(estudante);
    }

    public Estudante loginEstudante(String cpf, String senha) {
        // alterar retorno diagrama
        return estudanteCollection.loginEstudante(cpf, senha);
    }

}
