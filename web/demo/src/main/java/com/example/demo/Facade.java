package com.example.demo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

    public void cadastrarLivro(String nome, String edicao, int quantidade) {
        livroService.cadastroLivro(nome, edicao, quantidade);
    }

    public void atualizarLivro(Livro livro) {
        livroService.atualizarLivro(livro);
    }
    
    public boolean reservarLivro(Long idLivro, Long idEstudante) {
        boolean res = livroService.reservarLivroById(idLivro);
        if (res) {
            emprestimoService.adicionarEmprestimo(idLivro, idEstudante, LocalDate.now());
            return true;
        } 
        return false;
    }

    public List<Livro> buscarTodosLivros() {
        return livroService.buscarTodosLivros();
    }

    public void adicionarEmprestimo(Long idLivro, Long idEstudante, LocalDate dataInicio){
        emprestimoService.adicionarEmprestimo(idLivro, idEstudante, dataInicio);
    }

    public List<Emprestimo> buscarPorEstudante(Long idEstudante) {
        return emprestimoService.buscarPorEstudante(idEstudante);
    }

    public String cadastraFuncionario(String nome, String cpf, String senha) {
        String res = funcionarioService.cadastrarFuncionario(nome, cpf, senha);
        return res;
    }

    public Funcionario loginFuncionario(String cpf, String senha) {
        return funcionarioService.logiFuncionario(cpf, senha);
    }
}
