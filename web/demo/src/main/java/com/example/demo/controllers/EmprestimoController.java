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

@Controller
public class EmprestimoController {
    @Autowired
    private Facade facade;


    @GetMapping("/livros/emprestimos")
    public ModelAndView showEmprestimos(Long cpf, HttpSession session) {

        List<Emprestimo> emps = facade.buscarEmprestimoPorEstudante(cpf);
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
