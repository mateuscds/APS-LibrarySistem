package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.collection.LivroCollection;
import com.example.demo.model.Livro;

@Service
public class LivroService {
    @Autowired
    private LivroCollection livroCollection;

    public String cadastroLivro(String nome, String edicao, int quantidade) {
        return livroCollection.adicionarLivro(new Livro(nome, edicao, quantidade));
    }

    public void atualizarLivro(Livro livro) {
        livroCollection.atualizarEstoque(livro);
    }

    public Livro reservarLivroById(Long id) {
        return livroCollection.reservarLivroById(id);
    }

    public List<Livro> buscarTodosLivros() {
        return livroCollection.buscarTodosLivros();
    }
    public void devolverLivroById(Long id) {
        livroCollection.devolverLivroById(id);
    }
}
