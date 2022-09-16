package com.example.demo.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Facade;
import com.example.demo.model.Livro;

@Controller
public class CadastroLivroController {
    @Autowired
    private Facade facade;

    @GetMapping("/livros/cadastrarlivro")
    public ModelAndView showCadastroLivro(HttpSession session) {
        return new ModelAndView("/livro/cadastroLivro");
    }

    @PostMapping("/livros/cadastrarlivro")
    public ModelAndView cadastroLivro(String nome, String edicao, int quantidade, HttpSession session) {
        Boolean res = facade.cadastrarLivro(nome, edicao, quantidade);

        if (res == true) {
            return new ModelAndView("redirect:/funcionario");
        } else {
            ModelAndView mv = new ModelAndView("redirect:/livros/cadastrarlivro");
            mv.addObject("error", "exist");
            return mv;
        }
    }
}
