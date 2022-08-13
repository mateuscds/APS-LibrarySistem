package com.example.demo.collection;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Emprestimo;
import com.example.demo.repositories.EmprestimoRepository;

@Service
public class EmprestimoCollection {
    @Autowired
    EmprestimoRepository emprestimoRepository;

    public void adicionarEmprestimo(Long idLivro, Long idEstudante, LocalDate dataInicio) {
        LocalDate datafim = dataInicio.plusDays(7);
        Emprestimo emp = new Emprestimo(idLivro, idEstudante, dataInicio, datafim);
        emprestimoRepository.save(emp);
    }

    public List<Emprestimo> buscarPorEstudante(long idEstudante) {
        return emprestimoRepository.findAllByIdEstudante(idEstudante);
    }
}
