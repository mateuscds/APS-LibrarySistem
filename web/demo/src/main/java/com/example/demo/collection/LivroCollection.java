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

    public String adicionarLivro(Livro livro) {
        Livro livros = livroRepository.findByNomeAndEdicao(livro.getNome(), livro.getEdicao());
        if (livros == null) {
            livroRepository.save(livro);
            return "100";
        }
        return "livro já cadastrado";
    }

    public void atualizarEstoque(Livro livro) {
        Livro livro_bd = livroRepository.getReferenceById(livro.getId());
        livro_bd.setQuantidade(livro.getQuantidade());

        livroRepository.save(livro_bd);
    }

    public List<Livro> buscarTodosLivros() {
        return livroRepository.findAll();
    }

    public Livro reservarLivroById(Long id) {
        Livro livro_bd = livroRepository.getReferenceById(id);
        if (livro_bd.getQuantidade() > 0) {
            livro_bd.setQuantidade(livro_bd.getQuantidade()-1);

            livroRepository.save(livro_bd);
            return livro_bd;
        }
        
        return null;
    }

    public void devolverLivroById(Long id) {
        Livro livro_bd = livroRepository.getReferenceById(id);
        livro_bd.setQuantidade(livro_bd.getQuantidade()+1);
        livroRepository.save(livro_bd);
        return;
    }

    public void atualizarQuantidade(Long id, int quantidade) {
        Livro livro_bd = livroRepository.getReferenceById(id);
        livro_bd.setQuantidade(quantidade);
        livroRepository.save(livro_bd);
        return;
    }

    public void deletarLivro(Long id) {
        livroRepository.deleteById(id);
        return;
    }
}
