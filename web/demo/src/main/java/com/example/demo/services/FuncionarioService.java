package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.collection.FuncionarioCollection;
import com.example.demo.model.Funcionario;

@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioCollection funcionarioCollection;

    public String cadastrarFuncionario(String nome, String cpf, String senha) {

        boolean existe = funcionarioCollection.existeFuncionario(cpf);

        if (existe)
            return "CPF Já Existente";
         
        funcionarioCollection.cadastraFuncionario(new Funcionario(nome, cpf, senha));
        return "100"; // sucesso
    }

    public Funcionario logiFuncionario(String cpf, String senha) {
        // alterar retorno diagrama
        return funcionarioCollection.loginFuncionario(cpf, senha);
    }
}
