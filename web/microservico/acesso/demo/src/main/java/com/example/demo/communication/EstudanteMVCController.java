package com.example.demo.communication;

import com.example.demo.model.Estudante;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Facade;

@Controller
public class EstudanteMVCController {

  @Autowired
  private Facade facade;

  @PostMapping("/estudante/cpf")
  @ResponseBody
  public Long home(@RequestBody String cpf) {

    System.out.println("->>>>>>>>>>>>>>>>>>>> RET ESTUDANTE <<<<<<<<<<<<<<<<<<<<<<<<");
    return facade.buscaEstudante(cpf).getId();
  }

  // @PostMapping("/estoque/cadastro")
  // @ResponseBody
  // public Boolean cadastrarEstoque(@RequestBody Estoque estoque) {
  //   System.out.println(("->>>>>>>>>>>>>>>>>>>> CADASTRANDO ESTOQUE <<<<<<<<<<<<<<<<<<<<<<<<"));
  //   System.out.println((estoque));
  //   return estoqueController.cadastrarEstoque(estoque);
  // }
  
  // @PostMapping("/estoque/reservar")
  // @ResponseBody
  // public Boolean reservarEstoque(@RequestBody Long reserva) {
  //   System.out.println(("->>>>>>>>>>>>>>>>>>>> RESERVANDO ESTOQUE <<<<<<<<<<<<<<<<<<<<<<<<"));
  //   System.out.println(("->>>>>>>>>>>>>>>>>>>> "+ reserva+" <<<<<<<<<<<<<<<<<<<<<<<<"));
  //   // System.out.println(estoque);
  //   return estoqueController.reservarByid(reserva);
  // }
  // @GetMapping("/admin/")
  // public ModelAndView home() {
  //   ModelAndView mv = new ModelAndView("new");
  //   return mv;
  // }

}
