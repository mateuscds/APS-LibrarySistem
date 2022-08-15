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

    public String cadastrarEstudante(String nome, String cpf, String curso, String senha) {

        boolean existe =estudanteCollection.existeEstudante(cpf);

        if (existe)
            return "CPF Já Existente";
         
        estudanteCollection.cadastrarEstudante(new Estudante(nome, cpf, curso, senha));
        return "100"; // sucesso
    }

    public Estudante loginEstudante(String cpf, String senha) {
        // alterar retorno diagrama
        return estudanteCollection.loginEstudante(cpf, senha);
    }

    public Estudante buscaEstudante(String cpf) {
        return estudanteCollection.buscaEstudante(cpf);
    }

    public Estudante buscaEstudantePorId(Long id) {
        return estudanteCollection.buscaEstudantePorId(id);
    }
}
