package com.example.demo.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.PagamentoApiRequest;
import com.example.demo.dto.pagamentoApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class PagamentoAPIService {
    private String paymentAPIUrl = "http://localhost:3333/pagamento/librarysystem";

    public Boolean pagar(PagamentoApiRequest pagamento) {
      RestTemplate restTemplate = new RestTemplate();
      ObjectMapper mapper = new ObjectMapper();
      String body = "";
      try {
        body = mapper.writeValueAsString(pagamento);
        System.out.println(body);
      } catch (JsonProcessingException e) {
        e.printStackTrace();
      }
  
      HttpEntity<String> entity = new HttpEntity<>(body);

      System.out.println(entity);
  
      pagamentoApiResponse response = restTemplate.exchange(paymentAPIUrl, HttpMethod.POST, entity, pagamentoApiResponse.class).getBody();
      return response.getStatus().equals("success");
    }

}
