package com.example.demo.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.collection.EmprestimoCollection;
import com.example.demo.model.Emprestimo;

@Service
public class EmprestimoService {
    @Autowired
    private EmprestimoCollection emprestimoCollection;

    public void adicionarEmprestimo(Long idEstudante, Long idLivro, String nomeLivro, String edicaoLivro, LocalDate dataInicio) {
        emprestimoCollection.adicionarEmprestimo(idEstudante, idLivro, nomeLivro, edicaoLivro, dataInicio);
    }

    public List<Emprestimo> buscarPorEstudante(Long idEstudante) {
        return emprestimoCollection.buscarPorEstudante(idEstudante);
    }
}
