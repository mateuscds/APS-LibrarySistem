package com.example.demo.controllers;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Facade;
import com.example.demo.model.Estudante;

@Controller
public class LoginEstudanteController {
    
    @Autowired
    private Facade facade;

    @PostMapping("/estudante/login")
    public ModelAndView loginEstudante(String cpf, String senha, HttpSession session) {
        Estudante estudante = facade.loginEstudante(cpf, senha);
        if (estudante != null) {
            session.setAttribute("nome", estudante.getNome());
            session.setAttribute("cpf", estudante.getCpf());
            session.setAttribute("curso", estudante.getCurso());
            session.setAttribute("id", estudante.getId());
            session.setAttribute("tipo", "estudante");

            return new ModelAndView("redirect:/livros/estudante/" + "?id=" + String.valueOf(estudante.getId()));
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

    @GetMapping("/estudante/carregateste")
    public ModelAndView teste() {
        // facade.cadastrarLivro("Cálculo 1", "1ed", 5);
        facade.cadastrarEstudante("Ricardo", "10987654321", "Engenharia Mecânica", "90");
        
        // facade.adicionarEmprestimo(Long.valueOf(1), Long.valueOf(1), "livro_teste", "1ed", LocalDate.of(2022, 8, 1));
        
        // facade.cadastrarLivro("livro1", "1ed", 30);
        // facade.cadastrarLivro("livro2", "3ed", 3);

        // facade.cadastrarEstudante("lucas", "123", "ec", "123");

        return new ModelAndView("redirect:/estudante/login");
    }
}
