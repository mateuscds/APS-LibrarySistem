package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.collection.EstoqueCollection;
import com.example.demo.collection.LivroCollection;
import com.example.demo.model.Estoque;
import com.example.demo.model.Livro;

@Service
public class LivroService {
    @Autowired
    private LivroCollection livroCollection;

    @Autowired
    private EstoqueCollection estoqueCollection;

    public Boolean cadastroLivro(String nome, String edicao, int quantidade) {
        Livro l = new Livro(nome, edicao);
        Boolean res = livroCollection.adicionarLivro(l);
        if (res) {
            estoqueCollection.adicionarEstoque(new Estoque(l.getId(), nome, edicao, quantidade));
        }
        return res;
    }

    public void atualizarEstoque(Estoque estoque) {
        estoqueCollection.atualizarEstoque(estoque);
    }

    public void atualizarQuantidade(Long idEstoque, int quantidade) {
        estoqueCollection.atualizarQuantidade(idEstoque, quantidade);
    }

    public Estoque reservarLivroById(Long idEstoque) {
        return estoqueCollection.reservarLivroById(idEstoque);
    }

    public List<Livro> buscarTodosLivros() {
        return livroCollection.buscarTodosLivros();
    }
    public void devolverLivroById(Long idEstoque) {
        estoqueCollection.devolverLivroById(idEstoque);
    }

    public void deletarLivro(Long id) {
        livroCollection.deletarLivro(id);
    }

    public void deletarEstoque(Long id) {
        estoqueCollection.deletarEstoque(id);
    }

    public List<Estoque> buscarTodosEstoques() {
        return estoqueCollection.buscarTodosEstoques();
    }
}
