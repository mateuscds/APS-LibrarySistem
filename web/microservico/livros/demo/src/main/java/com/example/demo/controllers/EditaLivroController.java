package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
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
public class EditaLivroController {
    
    @Autowired
    private Facade facade;

    @GetMapping("/livros/editalivro")
    public ModelAndView editaLivro(HttpSession session) {
        ModelAndView mv = new ModelAndView("livro/editaLivro");
        mv.addObject("livros", facade.buscarTodosEstoques());
        return mv;
    }
}
