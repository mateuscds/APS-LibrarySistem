package com.example.demo.controllers;

import java.time.LocalDate;

import javax.servlet.http.HttpSession;

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
        Emprestimo emp = facade.buscaEmprestimoPorId(idEmprestimo);
        
        Double valor = ChronoUnit.DAYS.between(LocalDate.now(), emp.getDataTerminoEmprestimo()) * 2.;
        ModelAndView mv = new ModelAndView("/pagamento/showPagamento");
        mv.addObject("valor", Math.abs(valor));
        return mv;
    }

    @PostMapping("/livros/pagamento")
    public ModelAndView emitePagamento(String email, Double valor, HttpSession session) {
        boolean res = facade.emitePagamento(email, valor);

        if (res == true)
            return new ModelAndView("/pagamento/pagamentoSucesso");
        else
            return new ModelAndView("/pagamento/pagamentoFalha");
    }
    
}
