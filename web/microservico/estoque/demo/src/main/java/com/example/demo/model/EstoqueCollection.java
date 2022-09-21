package com.example.demo.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Estoque;
import com.example.demo.repositories.EstoqueRepository;

@Service
public class EstoqueCollection {
    @Autowired
    private EstoqueRepository estoqueRepository;

    public void atualizarEstoque(Estoque estoque) {
        estoqueRepository.save(estoque);
    }

    public void adicionarEstoque(Estoque estoque) {
        estoqueRepository.save(estoque);
    }

    public void atualizarQuantidade(Long idEstoque, int quantidade) {
        Estoque est = estoqueRepository.getReferenceById(idEstoque);
        est.setQuantidade(quantidade);
        estoqueRepository.save(est);
        return;
    }

    public void devolverLivroById(Long idEstoque) {
        Estoque est = estoqueRepository.getReferenceById(idEstoque);
        est.setQuantidade(est.getQuantidade()+1);
        estoqueRepository.save(est);
        return;
    }

    public Boolean reservarLivroById(Long id) {
        Estoque est = estoqueRepository.getReferenceById(id);
        if (est.getQuantidade() > 0) {
            est.setQuantidade(est.getQuantidade()-1);

            estoqueRepository.save(est);
            return true;
        }
        return false;
    }

    public List<Estoque> buscarTodosEstoques() {
        return estoqueRepository.findAll();
    }
    
    public Boolean cadastrarEstoque(Estoque estoque) {
        estoqueRepository.save(estoque);
        System.out.println(estoque);
        return true;
    }

    public void deletarEstoque(Long id) {
        estoqueRepository.deleteById(id);
    }
}
