package com.example.demo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.Estudante;
import com.example.demo.services.EstudanteService;
import com.example.demo.services.FuncionarioService;

import com.example.demo.model.Funcionario;


@Component
public class Facade {
    
    @Autowired
    private EstudanteService estudanteService;

    @Autowired
    private FuncionarioService funcionarioService;

    public Boolean cadastrarEstudante(String nome, String cpf, String curso, String senha) {
        return estudanteService.cadastrarEstudante(nome, cpf, curso, senha);
    }

    public Estudante loginEstudante(String cpf, String senha) {
        return estudanteService.loginEstudante(cpf, senha);
    }

    public Estudante buscaEstudante(String cpf) {
        return estudanteService.buscaEstudante(cpf);
    }

    public Estudante buscaEstudantePorId(Long id) {
        return estudanteService.buscaEstudantePorId(id);
    }

    public String cadastraFuncionario(String nome, String cpf, String senha) {
        String res = funcionarioService.cadastrarFuncionario(nome, cpf, senha);
        return res;
    }

    public Funcionario loginFuncionario(String cpf, String senha) {
        return funcionarioService.loginFuncionario(cpf, senha);
    }


}
