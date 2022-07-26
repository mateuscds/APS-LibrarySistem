package com.example.demo.controllers;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Facade;
import com.example.demo.model.Estudante;

@Controller
public class EstudanteController {
    
    @Autowired
    private Facade facade;

    @GetMapping("/estudante")
    public ModelAndView home(@RequestParam Long id, HttpSession session) {
        String curso = (String) session.getAttribute("curso");
        Long idEstudante = (Long) session.getAttribute("id");

        if(id == null) {
            return new ModelAndView("redirect:/estudante/login");
        } else {
            // List<Emprestimo> emps = facade.buscarEmprestimoPorEstudante(idEstudante);
            ModelAndView mv = new ModelAndView("redirect:/livros/estudante/emprestimos" + "?id=" + String.valueOf(id));
            return mv;
        }
    }

    public Estudante buscaEstudante(String cpf) {
        return facade.buscaEstudante(cpf);
    }

    // @GetMapping("/carregateste")
    // public ModelAndView teste() {
    //     facade.cadastrarLivro("Cálculo 1", "1ed", 5);
    //     facade.cadastrarEstudante("Ricardo", "10987654321", "Engenharia Mecânica", "90");
        
    //     facade.adicionarEmprestimo(Long.valueOf(1), Long.valueOf(1), "livro_teste", "1ed", LocalDate.of(2022, 8, 1));
        
    //     facade.cadastrarLivro("livro1", "1ed", 30);
    //     facade.cadastrarLivro("livro2", "3ed", 3);

    //     return new ModelAndView("redirect:/estudante/login");
    // }

}
