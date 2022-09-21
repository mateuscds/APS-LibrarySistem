package com.example.demo.controllers;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Facade;
import com.example.demo.dto.Emprestimo;
import com.example.demo.dto.Estudante;

@Controller
public class EmprestimoController {
    @Autowired
    private Facade facade;


    @GetMapping("/livros/emprestimos")
    public ModelAndView showEmprestimos(String cpf, HttpSession session) {

        Long id = facade.buscaEstudante(cpf);
        List<Emprestimo> emps = facade.buscarEmprestimoPorEstudante(id);
        System.out.println(emps.size());
        ModelAndView mv = new ModelAndView("livro/showEmprestimos");
        mv.addObject("emprestimos", emps);
        return mv;
    }

    @GetMapping("/livros/estudante/emprestimos")
    public ModelAndView showEmprestimosEstudante(@RequestParam Long id, String cpf, HttpSession session) {

        List<Emprestimo> emps = facade.buscarEmprestimoPorEstudante(id);
        ModelAndView mv = new ModelAndView("estudante/home");
        mv.addObject("emprestimos", emps);
        return mv;
    }


    @GetMapping("/livros/{livroId}/pegaremprestado/{estudanteId}")
    public ModelAndView pegarEmprestado(@PathVariable Long livroId, @PathVariable Long estudanteId, HttpSession session) {

        facade.reservarLivro(estudanteId, livroId);
        ModelAndView mv = new ModelAndView("redirect:/livros/funcionario"); 
        return mv;
    }

}
