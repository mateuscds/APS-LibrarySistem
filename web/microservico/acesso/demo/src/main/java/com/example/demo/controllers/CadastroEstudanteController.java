package com.example.demo.controllers;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Facade;
import com.example.demo.model.Estudante;

@Controller
public class CadastroEstudanteController {
    @Autowired
    private Facade facade;

    @GetMapping("/estudante/cadastro")
    public ModelAndView showCadastro() {
        return new ModelAndView("estudante/cadastro");
    }

    @PostMapping("/estudante/cadastro")
    public ModelAndView showCadastro(String nome, String cpf, String curso, String senha) {
        Boolean res = facade.cadastrarEstudante(nome, cpf, curso, senha);
        if (res) {
            return new ModelAndView("redirect:/estudante/login");
        } else{
            ModelAndView mv = new ModelAndView("redirect:/estudante/cadastro");
            mv.addObject("error", "exist");
            return mv;
        }
    }

}
