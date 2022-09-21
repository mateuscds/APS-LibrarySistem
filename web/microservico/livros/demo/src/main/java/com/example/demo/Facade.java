package com.example.demo;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.Comprovante;
import com.example.demo.dto.Emprestimo;
import com.example.demo.dto.Estoque;
import com.example.demo.dto.Estudante;
import com.example.demo.dto.Funcionario;
import com.example.demo.model.Livro;
import com.example.demo.services.EmprestimoService;
import com.example.demo.services.EstudanteAPIService;
import com.example.demo.services.LivroService;

@Component
public class Facade {
    

    @Autowired
    private LivroService livroService;

    @Autowired
    private EmprestimoService emprestimoService;

    @Autowired
    private EstudanteAPIService estudanteAPIService;

    public Boolean cadastrarLivro(String nome, String edicao, int quantidade) {
        return livroService.cadastroLivro(nome, edicao, quantidade);
    }

    public void atualizarEstoque(Estoque estoque) {
        livroService.atualizarEstoque(estoque);
    }
    
    public void atualizarQuantidadeLivro(Long id, int quantidade) {
        livroService.atualizarQuantidade(id, quantidade);
    }

    public void deletarLivro(Long id) {
        livroService.deletarLivro(id);
    }

    public Comprovante reservarLivro(Long idEstudante, Long idEstoque) {
        Boolean res = livroService.reservarLivroById(idEstoque);
        if (res == true) {
            List<Estoque> est = livroService.buscarTodosEstoques();
            Estoque estoque = null;
            for (Estoque e : est) {
                if (e.getId() == idEstoque) {
                    estoque = e;
                    break;
                }
            }
            emprestimoService.adicionarEmprestimo(idEstudante, idEstoque, estoque.getNome(), estoque.getEdicao(), LocalDate.now());
            return new Comprovante(idEstudante, estoque, LocalDate.now());
        } 

        return null;
    }

    public List<Livro> buscarTodosLivros() {
        return livroService.buscarTodosLivros();
    }

    public void adicionarEmprestimo( Long idEstudante, Long idLivro, String nomeLivro, String edicaoLivro, LocalDate dataInicio){
        emprestimoService.adicionarEmprestimo(idEstudante, idLivro, nomeLivro, edicaoLivro, dataInicio);
    }

    public List<Emprestimo> buscarEmprestimoPorEstudante(Long idEstudante) {
        return emprestimoService.buscarPorEstudante(idEstudante);
    }

    public void devolverLivro(Long idLivro) {
        livroService.devolverLivroById(idLivro);

    }

    public void atualizaStatusEmprestimo(Long id) {
        emprestimoService.atualizaStatus(id);
    }
    
    public Emprestimo buscaEmprestimoPorId(Long id) {
        return emprestimoService.buscaPorId(id);
    }

    public boolean emitirBoleto(String cpf, String email, Double valor) {
        return emprestimoService.emitirBoleto(cpf, email, valor);
    }

    public boolean verificarDataEmprestimo(Long idEmprestimo) {
        return emprestimoService.verificarDataEmprestimo(idEmprestimo);
    } 

    public Long buscaLivroEmprestimoPorId(Long idEmprestimo) {
        return emprestimoService.buscaLivroEmprestimoPorId(idEmprestimo);
    }

    public Double valorMultaEmprestimo(Long idEmprestimo) {
        return emprestimoService.valorMultaEmprestimo(idEmprestimo);
    }

    public List<Estoque> buscarTodosEstoques() {
        return livroService.buscarTodosEstoques();
    }
    public Long buscaEstudante(String cpf) {
        return estudanteAPIService.buscaEstudante(cpf);
    }

    public String buscaEstudanteId(Long id) {
        return estudanteAPIService.buscaEstudantePorId(id);
    }

    public void deletarEstoque(Long id) {
        livroService.deletarEstoque(id);
    }
}
