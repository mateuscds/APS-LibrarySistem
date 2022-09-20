package com.example.demo.services.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.Pagamento;
import com.example.demo.dto.PagamentoApiRequest;
import com.example.demo.dto.PagamentoBoleto;
import com.example.demo.services.PagamentoAPIService;

@Component
public class PagamentoAPIAdapter {
    @Autowired
    private PagamentoAPIService pagamentoAPIService;

    public Boolean pagar(Pagamento pagamento) {
        PagamentoApiRequest request = null;
        if (pagamento instanceof PagamentoBoleto){
            request = new PagamentoApiRequest((PagamentoBoleto) pagamento);
        }

        return pagamentoAPIService.pagar(request);
    }
}
