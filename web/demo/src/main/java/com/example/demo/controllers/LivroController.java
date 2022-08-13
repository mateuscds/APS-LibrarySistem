package com.example.demo.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Facade;
import com.example.demo.model.Livro;

@Controller
public class LivroController {
    
    @Autowired
    private Facade facade;

    // @PostMapping("/livros/cadastro")
    // public ModelAndView adicionarLivro(String nome, String edicao, int quantidade, HttpSession session) {
    //     facade.cadastrarLivro(nome, edicao, quantidade);

    // }

    @GetMapping("/livros")
    public ModelAndView mostrarLivros(HttpSession session) {
        Object client = session.getAttribute("tipo");

        facade.cadastrarLivro("livro1", "1ed", 30);
        facade.cadastrarLivro("livro2", "3ed", 3);

        List<Livro> livros = facade.buscarTodosLivros();
        ModelAndView mv;
        if (client.equals("funcionario")) {
            mv = new ModelAndView("livro/showFuncionario");
        } else {
            mv = new ModelAndView("livro/showEstudante");
        }
        mv.addObject("livros", livros);
        return mv;
    }

    @GetMapping("/livros/{livroId}/pegaremprestado")
    public ModelAndView pegarEmprestado(@PathVariable Long livroId, HttpSession session) {
        Object estudanteId = session.getAttribute("id"); 

        facade.reservarLivro(livroId, Long.parseLong(String.valueOf(estudanteId)));
        ModelAndView mv = new ModelAndView("redirect:/livros"); 
        return mv;
    }
}
