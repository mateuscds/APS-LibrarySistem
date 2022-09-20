package com.example.demo.communication;

import com.example.demo.model.Estoque;
import com.example.demo.model.EstoqueController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EstoqueMVCController {

  @Autowired
  private EstoqueController estoqueController;

  @GetMapping("/estoque/")
  @ResponseBody
  public List<Estoque> home() {

    System.out.println("->>>>>>>>>>>>>>>>>>>> RETORNANDO TODOS ESTOQUES <<<<<<<<<<<<<<<<<<<<<<<<");
    return estoqueController.getAllEstoque();
  }

  @PostMapping("/estoque/cadastro")
  @ResponseBody
  public Boolean cadastrarEstoque(@RequestBody Estoque estoque) {
    System.out.println(("->>>>>>>>>>>>>>>>>>>> CADASTRANDO ESTOQUE <<<<<<<<<<<<<<<<<<<<<<<<"));
    System.out.println((estoque));
    return estoqueController.cadastrarEstoque(estoque);
  }
  // @GetMapping("/admin/")
  // public ModelAndView home() {
  //   ModelAndView mv = new ModelAndView("new");
  //   return mv;
  // }

}
