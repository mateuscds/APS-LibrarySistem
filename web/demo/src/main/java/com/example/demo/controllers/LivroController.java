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
import com.example.demo.model.Estoque;
import com.example.demo.model.Livro;

@Controller
public class LivroController {
    
    @Autowired
    private Facade facade;

    @GetMapping("/livros")
    public ModelAndView mostrarLivros(HttpSession session) {
        Object client = session.getAttribute("tipo");

        List<Estoque> livros = facade.buscarTodosEstoques();
        ModelAndView mv;
        if (client.equals("funcionario")) {
            mv = new ModelAndView("livro/showFuncionario");
        } else {
            mv = new ModelAndView("livro/showEstudante");
        }
        mv.addObject("livros", livros);
        return mv;
    }   

    @GetMapping("/livros/{livroId}/deletar")
    public ModelAndView deletarLivro(@PathVariable Long livroId, HttpSession session) {

        facade.deletarLivro(livroId);
        return new ModelAndView("redirect:/livros/editalivro");
    }
}
