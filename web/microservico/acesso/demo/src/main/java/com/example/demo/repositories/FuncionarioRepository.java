package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    public Funcionario findByCpf(String cpf);
    public Funcionario findByCpfAndSenha(String cpf, String senha);
}
