package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.Emprestimo;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
    public List<Emprestimo> findAllByIdLivro(long idLivro);
    public List<Emprestimo> findAllByIdEstudante(long idEstudante);
    public List<Emprestimo> findAllByIdEstudanteAndIdLivro(long idEstudante, long idLivro);
}
