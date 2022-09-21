package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.stream.Collectors;


import reactor.core.publisher.Mono;

import com.example.demo.dto.Estoque;
import com.example.demo.dto.ReservaApiRequest;
import java.util.List;
import com.example.demo.dto.EstoqueAPIRequest;

@Component
public class EstoqueAPIService {

    @Autowired WebClient.Builder wBuilder;

    private WebClient client() {
      return wBuilder.baseUrl("lb://estoque").build();
    }

    // public List<Room> adicionarEstoque(Long hotelId) {
    //     Mono<List<Room>> response = client().get()
    //         .uri("/rooms/{hotelId}", hotelId)
    //         .retrieve()
    //         .bodyToMono(new ParameterizedTypeReference<List<Room>>() {});
    
    //     List<Room> rooms = response.block();
          
    //     return rooms.stream().collect(Collectors.toList());
    //   }

    // TODO Integrar com o serviço Estoque
    public List<Estoque> buscarTodosEstoques() {

        Mono<List<Estoque>> response = client().get()
            .uri("/estoque/")
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<List<Estoque>>() {});
    
        List<Estoque> estoques = response.block();
        
        System.out.println("->>>>>>>>>>>>>>>>>>>>>>>>>>>RECEBI DA API <<<<<<<<<<<<<<<<<<<<=-");

        return estoques.stream().collect(Collectors.toList());
    }
    
    public Boolean adicionarEstoque(Estoque estoque) {
        Mono<Boolean> response = client().post()
            .uri("/estoque/cadastro")
            .body(Mono.just(estoque), Estoque.class)
            .retrieve()
            .bodyToMono(Boolean.class); 
        
        System.out.println("->>>>>>>>>>>>>>>>>>>>>>>>>>>ENVIEI POST <<<<<<<<<<<<<<<<<<<<=-");
        Boolean res = response.block();
        System.out.println(res);

        return res;       

        // Mono<List<Room>> response = client().get()
        //     .uri("/rooms/{hotelId}", hotelId)
        //     .retrieve()
        //     .bodyToMono(new ParameterizedTypeReference<List<Room>>() {});
    
        // List<Room> rooms = response.block();
          
        // return rooms.stream().collect(Collectors.toList());
    }

    public void devolverLivroById(Long idEstoque) {
        Mono<List<Estoque>> response = client().get()
        .uri("/rooms/{hotelId}", idEstoque)
        .retrieve()
        .bodyToMono(new ParameterizedTypeReference<List<Estoque>>() {});

        List<Estoque> rooms = response.block();
          
        return;
    }

    public void atualizarEstoque(Estoque estoque) {
        System.out.println("->>>>>>>>>>>>>>>>>>>>>>>>>>> TO NO ERRADO <<<<<<<<<<<<<<<<<<<<=-");
        return;
    }

    public void atualizarQuantidade(Long idEstoque, int quantidade) {
        EstoqueAPIRequest estoqueAPIRequest = new EstoqueAPIRequest();
        estoqueAPIRequest.id = idEstoque;
        estoqueAPIRequest.quantidade = quantidade;
        System.out.println("->>>>>>>>>>>>>>>>>>>>>>>>>>> ENVIANDO ATUALIZAR QUANTIDADE <<<<<<<<<<<<<<<<<<<<=-");
        Mono<Boolean> response = client().post()
        .uri("/estoque/atualizar")
        .body(Mono.just(estoqueAPIRequest), EstoqueAPIRequest.class)
        .retrieve()
        .bodyToMono(Boolean.class); 

        Boolean res = response.block();
    }

    public Boolean reservarLivroById(Long idEstoque) {

        Mono<Boolean> response = client().post()
        .uri("/estoque/reservar")
        .body(Mono.just(idEstoque), Long.class)
        .retrieve()
        .bodyToMono(Boolean.class); 
    
        System.out.println("->>>>>>>>>>>>>>>>>>>>>>>>>>>ENVIEI POST <<<<<<<<<<<<<<<<<<<<=-");
        Boolean res = response.block();
        System.out.println(res);

        return res;
    }
}
