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
public class EditaQuantidadeLivroController {

    @Autowired
    private Facade facade;

    @GetMapping("/livros/{livroId}/editaquantidade")
    public ModelAndView showEditaQuantidadeLivro(@PathVariable Long livroId, HttpSession session) {
        ModelAndView mv = new ModelAndView("livro/editaQuantidadeLivro");
        mv.addObject("livroindex", livroId);

        return mv;
    }

    @PostMapping("/livros/{livroId}/editaquantidade")
    public ModelAndView editaQuantidadeLivro(@PathVariable Long livroId, int quantidade, HttpSession session) {
        System.out.println(livroId);
        System.out.println(quantidade);
        
        facade.atualizarQuantidadeLivro(livroId, quantidade);
        return new ModelAndView("redirect:/livros/editalivro");
    }
}
