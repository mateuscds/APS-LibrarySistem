package com.example.demo.controllers;

import java.time.LocalDate;

import javax.servlet.http.HttpSession;

import org.hibernate.hql.internal.ast.tree.IdentNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import java.time.temporal.ChronoUnit;

import com.example.demo.Facade;
import com.example.demo.model.Emprestimo;

@Controller
public class PagamentoController {
    @Autowired
    Facade facade;

    @GetMapping("/livros/pagamento/{idEmprestimo}")
    public ModelAndView pagamento(@PathVariable Long idEmprestimo, HttpSession session) {
        Double valor = facade.valorMultaEmprestimo(idEmprestimo);
        ModelAndView mv = new ModelAndView("/pagamento/showPagamento");
        mv.addObject("valor", Math.abs(valor));
        mv.addObject("idemprestimo", idEmprestimo);
        return mv;
    }

    @PostMapping("/livros/pagamento/{idEmprestimo}")
    public ModelAndView emitePagamento(@PathVariable Long idEmprestimo, String email, Double valor, HttpSession session) {
        boolean res = facade.emitePagamento(email, valor);

        if (res == true) {
            facade.atualizaStatusEmprestimo(idEmprestimo);
            return new ModelAndView("/pagamento/pagamentoSucesso");
        }
        else
            return new ModelAndView("/pagamento/pagamentoFalha");
    }
    
}
