package com.example.demo.collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Estudante;
import com.example.demo.repositories.EstudanteRepository;

@Service
public class EstudanteCollection {
    @Autowired
    private EstudanteRepository estudanteRepository;

    public Estudante loginEstudante(String cpf, String senha) {
        // alterar no diagrama, acredito que faz mais sentido ser passado as informações do estudante para o Service;
        return estudanteRepository.findByCpfAndSenha(cpf, senha);
    }

    public void cadastrarEstudante(Estudante estudante) {
        estudanteRepository.save(estudante);
    }

    public boolean existeEstudante(String cpf) {
        Estudante busca = estudanteRepository.findByCpf(cpf);
        if (busca == null) 
            return false;
        else 
            return true;
    }

    public Estudante buscaEstudante(String cpf) {
        return estudanteRepository.findByCpf(cpf);
    }

    public Estudante buscaEstudantePorId(Long id) {
        return estudanteRepository.getReferenceById(id);
    }

}
