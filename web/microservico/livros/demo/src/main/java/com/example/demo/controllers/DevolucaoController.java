package com.example.demo.controllers;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dto.Estudante;
import com.example.demo.dto.Emprestimo;


import com.example.demo.Facade;

@Controller
public class DevolucaoController {
    @Autowired
    private Facade facade;

    @GetMapping("/livros/devolverlivro")
    public ModelAndView showDevolverLivro(HttpSession session) {
        return new ModelAndView("livro/devolverLivro");
    }

    @GetMapping("/livros/devolver/{idEmprestimo}")
    public ModelAndView devolucao(@PathVariable Long idEmprestimo, HttpSession session) {

        boolean onTime = facade.verificarDataEmprestimo(idEmprestimo);

        if (!onTime) {
            ModelAndView mv = new ModelAndView("redirect:/livros/pagamento/{idEmprestimo}");
            return mv;
        } else {

            Emprestimo emprestimo = facade.buscaEmprestimoPorId(idEmprestimo);

            System.out.println(emprestimo);
            String est = facade.buscaEstudanteId(emprestimo.getIdEstudante());

            facade.atualizaStatusEmprestimo(idEmprestimo);
            Long idLivro = facade.buscaLivroEmprestimoPorId(idEmprestimo);
            facade.devolverLivro(idLivro);

            ModelAndView mv = new ModelAndView("livro/devolucaoSucesso");
            mv.addObject("cpfEstudante", est);
            return mv;
        }
    }
}
