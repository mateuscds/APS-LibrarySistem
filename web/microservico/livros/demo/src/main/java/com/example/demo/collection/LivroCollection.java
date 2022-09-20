package com.example.demo.collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Livro;
import com.example.demo.repositories.LivroRepository;

import java.util.List;

@Service
public class LivroCollection {
    @Autowired
    LivroRepository livroRepository;

    public Boolean adicionarLivro(Livro livro) {
        Livro livros = livroRepository.findByNomeAndEdicao(livro.getNome(), livro.getEdicao());
        if (livros == null) {
            livroRepository.save(livro);
            return true;
        }
        return false;
    }

    public List<Livro> buscarTodosLivros() {
        return livroRepository.findAll();
    }

    public void deletarLivro(Long id) {
        livroRepository.deleteById(id);
        return;
    }
}
