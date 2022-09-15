package com.example.demo.controllers;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Facade;
import com.example.demo.model.Emprestimo;
import com.example.demo.model.Estudante;

@Controller
public class EmprestimoController {
    @Autowired
    private Facade facade;


    @GetMapping("/livros/emprestimos")
    public ModelAndView showEmprestimos(String cpf, HttpSession session) {

        Estudante est = facade.buscaEstudante(cpf);
        List<Emprestimo> emps = facade.buscarEmprestimoPorEstudante(est.getId());
        System.out.println(emps.size());
        ModelAndView mv = new ModelAndView("livro/showEmprestimos");
        mv.addObject("emprestimos", emps);
        return mv;
    }

    @GetMapping("/livros/{livroId}/pegaremprestado")
    public ModelAndView pegarEmprestado(@PathVariable Long livroId, HttpSession session) {
        Object estudanteId = session.getAttribute("id"); 

        facade.reservarLivro(Long.parseLong(String.valueOf(estudanteId)), livroId);
        ModelAndView mv = new ModelAndView("redirect:/livros"); 
        return mv;
    }

}
