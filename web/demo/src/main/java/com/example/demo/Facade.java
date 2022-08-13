package com.example.demo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

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

    public String cadastrarLivro(String nome, String edicao, int quantidade) {
        return livroService.cadastroLivro(nome, edicao, quantidade);
    }

    public void atualizarLivro(Livro livro) {
        livroService.atualizarLivro(livro);
    }
    
    public boolean reservarLivro(Long idEstudante, Long idLivro) {
        Livro res = livroService.reservarLivroById(idLivro);
        if (res != null) {
            emprestimoService.adicionarEmprestimo(idEstudante, idLivro, res.getNome(), res.getEdicao(), LocalDate.now());
            return true;
        } 
        return false;
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

    public void devolverLivro(Long idEstudante, Long idLivro) {
        livroService.devolverLivroById(idLivro);

    }

    public void atualizaStatusEmprestimo(Long idEstudante, Long idLivro) {
        
    }

}
