package com.example.demo.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class PagamentoApiRequest implements Serializable{
    private String paymentType;
    private String value;
    private String dueDate;
    private String barCode;

    public PagamentoApiRequest(PagamentoBoleto pagamentoBoleto) {
        this.paymentType = "boleto";
        this.value = pagamentoBoleto.getValue();
        this.dueDate = pagamentoBoleto.getDueDate();
        this.barCode = pagamentoBoleto.getBarCode();
    }
}
