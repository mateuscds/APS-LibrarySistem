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

    public void cadastroLivro(String nome, String edicao, int quantidade) {
        livroCollection.adicionarLivro(new Livro(nome, edicao, quantidade));
    }

    public void atualizarLivro(Livro livro) {
        livroCollection.atualizarEstoque(livro);
    }

    public boolean reservarLivroById(Long id) {
        return livroCollection.reservarLivroById(id);
    }

    public List<Livro> buscarTodosLivros() {
        return livroCollection.buscarTodosLivros();
    }
}
