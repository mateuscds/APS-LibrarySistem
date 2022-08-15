package com.example.demo.controllers;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Facade;
import com.example.demo.model.Emprestimo;
import com.example.demo.model.Estudante;

@Controller
public class EstudanteController {
    
    @Autowired
    private Facade facade;

    @GetMapping("/estudante")
    public ModelAndView home(HttpSession session) {
        String curso = (String) session.getAttribute("curso");
        Long idEstudante = (Long) session.getAttribute("id");

        if(curso == null) {
            return new ModelAndView("redirect:/estudante/login");
        } else {
            List<Emprestimo> emps = facade.buscarEmprestimoPorEstudante(idEstudante);

            ModelAndView mv = new ModelAndView("estudante/home");
            mv.addObject("emprestimos", emps);
            return mv;
        }
    }

    @PostMapping("/estudante/login")
    public ModelAndView loginEstudante(String cpf, String senha, HttpSession session) {
        Estudante estudante = facade.loginEstudante(cpf, senha);
        System.out.println("Aentrei aqui");
        if (estudante != null) {
            session.setAttribute("nome", estudante.getNome());
            session.setAttribute("cpf", estudante.getCpf());
            session.setAttribute("curso", estudante.getCurso());
            session.setAttribute("id", estudante.getId());
            session.setAttribute("tipo", "estudante");
            return new ModelAndView("redirect:/livros");
        } else {
            ModelAndView mv = new ModelAndView("redirect:/estudante/login");
            mv.addObject("error", "login");
            return mv;
        }
    }

    @GetMapping("/estudante/login")
    public ModelAndView register() {
        return new ModelAndView("estudante/estudante");
    }

    @GetMapping("/estudante/cadastro")
    public ModelAndView showCadastro() {
        return new ModelAndView("estudante/cadastro");
    }

    @PostMapping("/estudante/cadastro")
    public ModelAndView showCadastro(String nome, String cpf, String curso, String senha) {
        String res = facade.cadastrarEstudante(nome, cpf, curso, senha);
        
        if (res.equals("100")) {
            return new ModelAndView("redirect:/estudante/login");
        } else{
            ModelAndView mv = new ModelAndView("redirect:/estudante/cadastro");
            mv.addObject("error", "exist");
            return mv;
        }
    }


    @GetMapping("/carregateste")
    public ModelAndView teste() {
        facade.cadastrarLivro("Cálculo 1", "1ed", 5);
        facade.cadastrarEstudante("Ricardo", "10987654321", "Engenharia Mecânica", "90");
        
        facade.adicionarEmprestimo(Long.valueOf(1), Long.valueOf(1), "livro_teste", "1ed", LocalDate.of(2022, 8, 1));
        
        facade.cadastrarLivro("livro1", "1ed", 30);
        facade.cadastrarLivro("livro2", "3ed", 3);

        return new ModelAndView("redirect:/estudante/login");
    }

}
