package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.stream.Collectors;


import reactor.core.publisher.Mono;

import com.example.demo.dto.Estudante;
import com.example.demo.dto.ReservaApiRequest;
import java.util.List;


@Component
public class EstudanteAPIService {

    @Autowired WebClient.Builder wBuilder;

    private WebClient client() {
      return wBuilder.baseUrl("lb://acesso").build();
    }

    // public List<Room> adicionarEstoque(Long hotelId) {
    //     Mono<List<Room>> response = client().get()
    //         .uri("/rooms/{hotelId}", hotelId)
    //         .retrieve()
    //         .bodyToMono(new ParameterizedTypeReference<List<Room>>() {});
    
    //     List<Room> rooms = response.block();
          
    //     return rooms.stream().collect(Collectors.toList());
    //   }
    
    public Long buscaEstudante(String cpf) {
        Mono<Long> response = client().post()
            .uri("/estudante/cpf")
            .body(Mono.just(cpf), String.class)
            .retrieve()
            .bodyToMono(Long.class); 
        
        System.out.println("->>>>>>>>>>>>>>>>>>>>>>>>>>>ENVIEI POST <<<<<<<<<<<<<<<<<<<<=-");
        Long res = response.block();
        System.out.println(res);

        return res;             
    }

    public String buscaEstudantePorId(Long id) {
        Mono<String> response = client().post()
            .uri("/estudante/buscacpf")
            .body(Mono.just(id), Long.class)
            .retrieve()
            .bodyToMono(String.class); 
        
        System.out.println("->>>>>>>>>>>>>>>>>>>>>>>>>>>ENVIEI POST <<<<<<<<<<<<<<<<<<<<=-");
        String res = response.block();
        System.out.println(res);

        return res;             
    }

    // // TODO Integrar com o serviço Estoque
    // public List<Estoque> buscarTodosEstoques() {

    //     Mono<List<Estoque>> response = client().get()
    //         .uri("/estudante/cpf")
    //         .retrieve()
    //         .bodyToMono(new ParameterizedTypeReference<List<Estoque>>() {});
    
    //     List<Estoque> estoques = response.block();
        
    //     System.out.println("->>>>>>>>>>>>>>>>>>>>>>>>>>>RECEBI DA API <<<<<<<<<<<<<<<<<<<<=-");

    //     return estoques.stream().collect(Collectors.toList());
    // }
    
    // public Boolean adicionarEstoque(Estoque estoque) {
    //     Mono<Boolean> response = client().post()
    //         .uri("/estoque/cadastro")
    //         .body(Mono.just(estoque), Estoque.class)
    //         .retrieve()
    //         .bodyToMono(Boolean.class); 
        
    //     System.out.println("->>>>>>>>>>>>>>>>>>>>>>>>>>>ENVIEI POST <<<<<<<<<<<<<<<<<<<<=-");
    //     Boolean res = response.block();
    //     System.out.println(res);

    //     return res;       

    //     // Mono<List<Room>> response = client().get()
    //     //     .uri("/rooms/{hotelId}", hotelId)
    //     //     .retrieve()
    //     //     .bodyToMono(new ParameterizedTypeReference<List<Room>>() {});
    
    //     // List<Room> rooms = response.block();
          
    //     // return rooms.stream().collect(Collectors.toList());
    // }

    // public void devolverLivroById(Long idEstoque) {
    //     Mono<List<Estoque>> response = client().get()
    //     .uri("/rooms/{hotelId}", idEstoque)
    //     .retrieve()
    //     .bodyToMono(new ParameterizedTypeReference<List<Estoque>>() {});

    //     List<Estoque> rooms = response.block();
          
    //     return;
    // }

    // public void atualizarEstoque(Estoque estoque) {
    //     return;
    // }

    // public void atualizarQuantidade(Long idEstoque, int quantidade) {
    //     return;
    // }

    // public Boolean reservarLivroById(Long idEstoque) {

    //     Mono<Boolean> response = client().post()
    //     .uri("/estoque/reservar")
    //     .body(Mono.just(idEstoque), Long.class)
    //     .retrieve()
    //     .bodyToMono(Boolean.class); 
    
    //     System.out.println("->>>>>>>>>>>>>>>>>>>>>>>>>>>ENVIEI POST <<<<<<<<<<<<<<<<<<<<=-");
    //     Boolean res = response.block();
    //     System.out.println(res);

    //     return res;
    // }
}
