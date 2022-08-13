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

    public void adicionarEmprestimo(Long idEstudante, Long idLivro, String nomeLivro, String edicaoLivro, LocalDate dataInicio) {
        LocalDate dataFim = dataInicio.plusDays(7);
        Emprestimo emp = new Emprestimo(idEstudante, idLivro, nomeLivro, edicaoLivro, dataInicio, dataFim);
        emprestimoRepository.save(emp);
    }

    public List<Emprestimo> buscarPorEstudante(long idEstudante) {
        return emprestimoRepository.findAllByIdEstudante(idEstudante);
    }

    public void atualizaStatusEmprestimo(long idEstudante, long idLivro) {
        List<Emprestimo> emps = emprestimoRepository.findAllByIdEstudanteAndIdLivro(idEstudante, idLivro);

        // Emprestimo emp_maiorTempo;
        for (Emprestimo emp : emps) {
            if(emp.isStatusAberto()) {
                emp.setStatusAberto(false);
                emprestimoRepository.save(emp);
                break;
            }
        }
    }
}
