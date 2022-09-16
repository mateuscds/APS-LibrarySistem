package com.example.demo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.Comprovante;
import com.example.demo.model.Emprestimo;
import com.example.demo.model.Estudante;
import com.example.demo.model.Funcionario;
import com.example.demo.model.Livro;
import com.example.demo.services.EmprestimoService;
import com.example.demo.services.EstudanteService;
import com.example.demo.services.FuncionarioService;
import com.example.demo.services.LivroService;


@Component
public class Facade {
    
    @Autowired
    private EstudanteService estudanteService;

    @Autowired
    private LivroService livroService;

    @Autowired
    private EmprestimoService emprestimoService;

    @Autowired
    private FuncionarioService funcionarioService;

    public String cadastrarEstudante(String nome, String cpf, String curso, String senha) {
        String res = estudanteService.cadastrarEstudante(nome, cpf, curso, senha);
        return res;
    }

    public Estudante loginEstudante(String cpf, String senha) {
        return estudanteService.loginEstudante(cpf, senha);
    }

    public Estudante buscaEstudante(String cpf) {
        return estudanteService.buscaEstudante(cpf);
    }

    public Estudante buscaEstudantePorId(Long id) {
        return estudanteService.buscaEstudantePorId(id);
    }

    public String cadastrarLivro(String nome, String edicao, int quantidade) {
        return livroService.cadastroLivro(nome, edicao, quantidade);
    }

    public void atualizarLivro(Livro livro) {
        livroService.atualizarLivro(livro);
    }
    
    public void atualizarQuantidadeLivro(Long id, int quantidade) {
        livroService.atualizarQuantidade(id, quantidade);
    }

    public void deletarLivro(Long id) {
        livroService.deletarLivro(id);
    }

    public Comprovante reservarLivro(Long idEstudante, Long idLivro) {
        Livro res = livroService.reservarLivroById(idLivro);
        if (res != null) {
            emprestimoService.adicionarEmprestimo(idEstudante, idLivro, res.getNome(), res.getEdicao(), LocalDate.now());
            Estudante est = estudanteService.buscaEstudantePorId(idEstudante);
            return new Comprovante(est, res, LocalDate.now());
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

    public String cadastraFuncionario(String nome, String cpf, String senha) {
        String res = funcionarioService.cadastrarFuncionario(nome, cpf, senha);
        return res;
    }

    public Funcionario loginFuncionario(String cpf, String senha) {
        return funcionarioService.loginFuncionario(cpf, senha);
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

    public boolean emitePagamento(String email, Double valor) {
        return emprestimoService.pagar(email, valor);
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
}
