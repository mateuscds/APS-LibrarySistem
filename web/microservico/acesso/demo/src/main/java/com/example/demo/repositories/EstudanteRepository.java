package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Estudante;

@Repository
public interface EstudanteRepository extends JpaRepository<Estudante, Long> {
    public Estudante findByCpfAndSenha(String cpf, String senha);
    public Estudante findByCpf(String cpf);
}
