package com.example.demo.services;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.collection.LivroCollection;
import com.example.demo.services.EstoqueAPIService;
import com.example.demo.dto.Estoque;
import com.example.demo.model.Livro;

@Service
public class LivroService {
    @Autowired
    private LivroCollection livroCollection;

    @Autowired
    private EstoqueAPIService estoqueAPIService;


    public Boolean cadastroLivro(String nome, String edicao, int quantidade) {
        Livro l = new Livro(nome, edicao);
        Boolean res = livroCollection.adicionarLivro(l);
        if (res) {
            estoqueAPIService.adicionarEstoque(new Estoque(l.getId(), nome, edicao, quantidade));
        }
        return res;
    }

    public void atualizarEstoque(Estoque estoque) {
        estoqueAPIService.atualizarEstoque(estoque);
    }

    public void atualizarQuantidade(Long idEstoque, int quantidade) {
        estoqueAPIService.atualizarQuantidade(idEstoque, quantidade);
    }

    public Estoque reservarLivroById(Long idEstoque) {
        return estoqueAPIService.reservarLivroById(idEstoque);
    }

    public List<Livro> buscarTodosLivros() {
        return livroCollection.buscarTodosLivros();
    }
    public void devolverLivroById(Long idEstoque) {
        estoqueAPIService.devolverLivroById(idEstoque);
    }

    public void deletarLivro(Long id) {
        livroCollection.deletarLivro(id);
    }

    public List<Estoque> buscarTodosEstoques() {
        List<Estoque> a = estoqueAPIService.buscarTodosEstoques();
        return a;
    }
}
