package com.example.demo.model;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Component;

import com.example.demo.model.Estoque;

@Component
public class EstoqueController {
    
    @Autowired
    private EstoqueCollection estoqueCollection;

    public List<Estoque> getAllEstoque() {
        return estoqueCollection.buscarTodosEstoques();
    }
    
    public Boolean cadastrarEstoque(Estoque estoque) {
        return estoqueCollection.cadastrarEstoque(estoque);
    }

    public Boolean reservarByid(Long id) {
        return estoqueCollection.reservarLivroById(id);
    }
    
    public Boolean atualizaQuantidad(Long id, int quantidade) {
        estoqueCollection.atualizarQuantidade(id, quantidade);
        return true;
    }
    // public ModelAndView deletarLivro(@PathVariable Long livroId, HttpSession session) {

    //     facade.deletarLivro(livroId);
    //     return new ModelAndView("redirect:/livros/editalivro");
    // }
}
