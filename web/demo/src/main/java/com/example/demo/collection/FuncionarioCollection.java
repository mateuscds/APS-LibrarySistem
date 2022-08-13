package com.example.demo.collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Funcionario;
import com.example.demo.repositories.FuncionarioRepository;

@Service
public class FuncionarioCollection {
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public Funcionario loginFuncionario(String cpf, String senha) {
        return funcionarioRepository.findByCpfAndSenha(cpf, senha);
    }

    public void cadastraFuncionario(Funcionario funcionario) {
        funcionarioRepository.save(funcionario);
    }

    public boolean existeFuncionario(String cpf) {
        Funcionario busca = funcionarioRepository.findByCpf(cpf);
        if (busca==null) return false;
        return true;
    }
}
