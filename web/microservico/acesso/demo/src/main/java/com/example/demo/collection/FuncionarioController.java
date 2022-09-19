package com.example.demo.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Facade;
import com.example.demo.model.Funcionario;

@Controller
public class FuncionarioController {
    @Autowired
    Facade facade;

    @GetMapping("/funcionario")
    public ModelAndView home(HttpSession session) {
        // cria estaticamente um funcionario e deixa logado
        facade.cadastraFuncionario("joao", "12345678910", "123");
        Funcionario f = facade.loginFuncionario("12345678910", "123");
        
        session.setAttribute("nome", f.getNome());
        session.setAttribute("cpf", f.getCpf());
        session.setAttribute("id", f.getId());
        session.setAttribute("tipo", "funcionario");
        
        return new ModelAndView("redirect:/livros");
    }
}
