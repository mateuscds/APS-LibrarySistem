package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoBoleto extends Pagamento{
    private String cpf;
    private String value;
    private String dueDate;
    private String barCode;
    
}
