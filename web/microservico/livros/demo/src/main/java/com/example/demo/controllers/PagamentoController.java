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
import com.example.demo.dto.Emprestimo;
import com.example.demo.dto.Estudante;


@Controller
public class PagamentoController {
    @Autowired
    Facade facade;

    @GetMapping("/livros/pagamento/{idEmprestimo}")
    public ModelAndView pagamento(@PathVariable Long idEmprestimo, HttpSession session) {
        Double valor = facade.valorMultaEmprestimo(idEmprestimo);
        ModelAndView mv = new ModelAndView("pagamento/showPagamento");
        mv.addObject("valor", valor);
        mv.addObject("idemprestimo", idEmprestimo);
        return mv;
    }

    @PostMapping("/livros/pagamento/{idEmprestimo}")
    public ModelAndView emitePagamento(@PathVariable Long idEmprestimo, String email, Double valor, HttpSession session) {
        Emprestimo emp = facade.buscaEmprestimoPorId(idEmprestimo);
        boolean res = facade.emitirBoleto(facade.buscaEstudanteId(emp.getIdEstudante()), email, valor);

        if (res == true) {

            Emprestimo emprestimo = facade.buscaEmprestimoPorId(idEmprestimo);
            String cpf = facade.buscaEstudanteId(emprestimo.getIdEstudante());

            facade.atualizaStatusEmprestimo(idEmprestimo);
            ModelAndView mv = new ModelAndView("pagamento/pagamentoSucesso");

            mv.addObject("cpfEstudante", cpf);

            return mv;
        }
        else
            return new ModelAndView("pagamento/pagamentoFalha");
    }
    
}
