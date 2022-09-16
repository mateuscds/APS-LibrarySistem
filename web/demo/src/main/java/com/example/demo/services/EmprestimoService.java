package com.example.demo.services;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.collection.EmprestimoCollection;
import com.example.demo.dto.pagamentoApiResponse;
import com.example.demo.model.Emprestimo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EmprestimoService {
    @Autowired
    private EmprestimoCollection emprestimoCollection;

    public void adicionarEmprestimo(Long idEstudante, Long idLivro, String nomeLivro, String edicaoLivro, LocalDate dataInicio) {
        emprestimoCollection.adicionarEmprestimo(idEstudante, idLivro, nomeLivro, edicaoLivro, dataInicio);
    }

    public List<Emprestimo> buscarPorEstudante(Long idEstudante) {
        return emprestimoCollection.buscarPorEstudante(idEstudante);
    }

    public Emprestimo buscaPorId(Long id) {
        return emprestimoCollection.buscaPorId(id);
    }
    
    public void atualizaStatus(Long id) {
        emprestimoCollection.atualizaStatus(id);
    }

    public boolean verificarDataEmprestimo(Long id) {
      LocalDate limit = emprestimoCollection.dataTerminoEmprestimo(id);
      LocalDate today = LocalDate.now();

      if (today.isAfter(limit))
        return false;
      return true;
    }

    public Long buscaLivroEmprestimoPorId(Long id) {
      return emprestimoCollection.buscaLivroEmprestimoPorId(id);
    }

    public Double valorMultaEmprestimo(Long id) {
      Emprestimo emp = emprestimoCollection.buscaPorId(id);
      if (!this.verificarDataEmprestimo(id))
        return ChronoUnit.DAYS.between(LocalDate.now(), emp.getDataTerminoEmprestimo()) * 2.;
      return 0.;
    }

    private String paymentAPIUrl = "http://localhost:3333/pagamento/librarysystem";

    public Boolean pagar(String email, Double valor) {
      RestTemplate restTemplate = new RestTemplate();
      ObjectMapper mapper = new ObjectMapper();
      String body = "";
      try {
        body = mapper.writeValueAsString(valor);
        System.out.println(body);
      } catch (JsonProcessingException e) {
        e.printStackTrace();
      }
  
      // // HttpHeaders headers = new HttpHeaders();
      // // headers.setContentType(MediaType.APPLICATION_JSON);
      // // headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
      // System.out.println(headers);
  
      HttpEntity<String> entity = new HttpEntity<>(body);
  
      System.out.println(entity);
  
      pagamentoApiResponse response = restTemplate.exchange(paymentAPIUrl, HttpMethod.POST, entity, pagamentoApiResponse.class).getBody();
      return response.getStatus().equals("success");
    }
}
