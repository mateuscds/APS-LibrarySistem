package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import java.util.List;
import java.util.ArrayList;

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
import com.example.demo.model.Livro_estoque;


@Controller
public class EditaLivroController {
    
    @Autowired
    private Facade facade;

    @GetMapping("/livros/editalivro")
    public ModelAndView editaLivro(HttpSession session) {
        ModelAndView mv = new ModelAndView("/livro/editaLivro");
        List<Livro> livros = facade.buscarTodosLivros();
        List<Estoque> estoques = facade.buscarTodosEstoques();
        System.out.println(livros);
        System.out.println(estoques);

        List<Livro_estoque> livros_estoque = new ArrayList<Livro_estoque>();

        for (int i = 0; i < livros.size(); i++) {
            int qtde = -1;
            for (int j = 0; j < estoques.size(); j++) {
                if (livros.get(i).getId() == estoques.get(j).getIdLivro()) {
                    qtde = estoques.get(j).getQuantidade();
                }
            }
            Livro_estoque livro_estoque = new Livro_estoque(livros.get(i).getNome(), livros.get(i).getEdicao(), qtde, livros.get(i).getId());
            livros_estoque.add(livro_estoque);
        }

        mv.addObject("livros", livros_estoque);
        return mv;
    }
}
