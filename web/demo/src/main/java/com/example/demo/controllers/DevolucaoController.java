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

public class DevolucaoController {
    @Autowired
    private Facade facade;

    // TODO Alterar arquitetura adicionando DevolucaoController
    @GetMapping("/livros/devolverlivro")
    public ModelAndView showDevolverLivro(HttpSession session) {
        return new ModelAndView("/livro/devolverLivro");
    }

    @GetMapping("/livros/devolver/{idEmprestimo}")
    public ModelAndView devolucao(@PathVariable Long idEmprestimo, HttpSession session) {

        Emprestimo emp = facade.buscaEmprestimoPorId(idEmprestimo);
        LocalDate today = LocalDate.now();
        LocalDate limit = emp.getDataTerminoEmprestimo();

        if (today.isAfter(limit)) {
            ModelAndView mv = new ModelAndView("redirect:/livros/pagamento/{idEmprestimo}");
            return mv;
        } else {
            facade.atualizaStatusEmprestimo(emp.getId());
            Long idLivro = emp.getIdLivro();
            facade.devolverLivro(idLivro);

            return new ModelAndView("/livros/devolucaoSucesso");
        }
    }

}
