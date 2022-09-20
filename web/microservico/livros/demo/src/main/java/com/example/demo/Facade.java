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
import com.example.demo.services.LivroService;

@Component
public class Facade {
    

    @Autowired
    private LivroService livroService;

    @Autowired
    private EmprestimoService emprestimoService;

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
        // Estoque est = livroService.reservarLivroById(idEstoque);
        // if (est != null) {
        //     emprestimoService.adicionarEmprestimo(idEstudante, idEstoque, est.getNome(), est.getEdicao(), LocalDate.now());
        //     Estudante estudante = estudanteService.buscaEstudantePorId(idEstudante);
        //     return new Comprovante(estudante, est, LocalDate.now());
        // } 

        return null;
    }

    public List<Livro> buscarTodosLivros() {
        return livroService.buscarTodosLivros();
    }

    public void adicionarEmprestimo( Long idEstudante, Long idLivro, String nomeLivro, String edicaoLivro, LocalDate dataInicio){
        // emprestimoService.adicionarEmprestimo(idEstudante, idLivro, nomeLivro, edicaoLivro, dataInicio);
    }

    public List<Emprestimo> buscarEmprestimoPorEstudante(Long idEstudante) {
        return new ArrayList<Emprestimo>(); //emprestimoService.buscarPorEstudante(idEstudante);
    }

    public void devolverLivro(Long idLivro) {
        livroService.devolverLivroById(idLivro);

    }

    public void atualizaStatusEmprestimo(Long id) {
        // emprestimoService.atualizaStatus(id);
    }
    
    public Emprestimo buscaEmprestimoPorId(Long id) {
        return new Emprestimo();//emprestimoService.buscaPorId(id);
    }

    public boolean emitirBoleto(String cpf, String email, Double valor) {
        return false;//emprestimoService.emitirBoleto(cpf, email, valor);
    }

    public boolean verificarDataEmprestimo(Long idEmprestimo) {
        return true;//return emprestimoService.verificarDataEmprestimo(idEmprestimo);
    } 

    public Long buscaLivroEmprestimoPorId(Long idEmprestimo) {
        return 1L;//emprestimoService.buscaLivroEmprestimoPorId(idEmprestimo);
    }

    public Double valorMultaEmprestimo(Long idEmprestimo) {
        return 0.;//emprestimoService.valorMultaEmprestimo(idEmprestimo);
    }

    public List<Estoque> buscarTodosEstoques() {
        return livroService.buscarTodosEstoques();
    }
    public Estudante buscaEstudante(String cpf) {
        return new Estudante();
    }
}
