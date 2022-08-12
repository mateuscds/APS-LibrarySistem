package com.example.demo.collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.model.Estudante;
import com.example.demo.repositories.EstudanteRepository;

@Service
public class EstudanteCollection {
    @Autowired
    private EstudanteRepository estudanteRepository;

    public Estudante consultarEstudante(String cpf, String senha) {
        return estudanteRepository.findByCpfAndSenha(cpf, senha);
    }
}
