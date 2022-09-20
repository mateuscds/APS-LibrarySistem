package com.example.demo.services;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.Pagamento;
import com.example.demo.dto.PagamentoBoleto;
import com.example.demo.dto.pagamentoApiResponse;
import com.example.demo.dto.Emprestimo;
import com.example.demo.services.adapter.PagamentoAPIAdapter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EmprestimoService {
    // @Autowired
    // private EmprestimoCollection emprestimoCollection;

    // @Autowired
    // private PagamentoAPIAdapter pagamentoAPIAdapter;

    // public void adicionarEmprestimo(Long idEstudante, Long idLivro, String nomeLivro, String edicaoLivro, LocalDate dataInicio) {
    //     emprestimoCollection.adicionarEmprestimo(idEstudante, idLivro, nomeLivro, edicaoLivro, dataInicio);
    // }

    // public List<Emprestimo> buscarPorEstudante(Long idEstudante) {
    //     return emprestimoCollection.buscarPorEstudante(idEstudante);
    // }

    // public Emprestimo buscaPorId(Long id) {
    //     return emprestimoCollection.buscaPorId(id);
    // }
    
    // public void atualizaStatus(Long id) {
    //     emprestimoCollection.atualizaStatus(id);
    // }

    // public boolean verificarDataEmprestimo(Long id) {
    //   LocalDate limit = emprestimoCollection.dataTerminoEmprestimo(id);
    //   LocalDate today = LocalDate.now();

    //   if (today.isAfter(limit))
    //     return false;
    //   return true;
    // }

    // public Long buscaLivroEmprestimoPorId(Long id) {
    //   return emprestimoCollection.buscaLivroEmprestimoPorId(id);
    // }

    // public Double valorMultaEmprestimo(Long id) {
    //   Emprestimo emp = emprestimoCollection.buscaPorId(id);
    //   if (!this.verificarDataEmprestimo(id))
    //     return Math.abs(ChronoUnit.DAYS.between(LocalDate.now(), emp.getDataTerminoEmprestimo())) * 2.;
    //   return 0.;
    // }

    // public Boolean emitirBoleto(String cpf, String email, Double valor) {
    //   Pagamento pagamento = new PagamentoBoleto(cpf, String.valueOf(valor), LocalDate.now().plusDays(3).toString(), "123456789");
    //   return pagamentoAPIAdapter.pagar(pagamento);
    // }
}
