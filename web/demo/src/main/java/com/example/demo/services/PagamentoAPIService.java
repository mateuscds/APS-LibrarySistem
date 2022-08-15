package com.example.demo.services;

import com.example.demo.dto.pagamentoApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class PagamentoAPIService {
  private String paymentAPIUrl = "http://localhost:3333/pagamento/librarysystem";

  public Boolean pagar(String email, Double valor) {
    RestTemplate restTemplate = new RestTemplate();
    ObjectMapper mapper = new ObjectMapper();
    String body = "";
    try {
      body = mapper.writeValueAsString(valor);
      System.out.println(body);
    } catch (JsonProcessingException e) {
      System.out.println("-------------------------------> CAIU NO ERRO");
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

