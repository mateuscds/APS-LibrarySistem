package com.example.demo.controllers;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Facade;
import com.example.demo.model.Emprestimo;
import com.example.demo.model.Livro;

@Controller
public class LivroController {
    
    @Autowired
    private Facade facade;

    @GetMapping("/livros")
    public ModelAndView mostrarLivros(HttpSession session) {
        Object client = session.getAttribute("tipo");

        facade.cadastrarLivro("livro1", "1ed", 30);
        facade.cadastrarLivro("livro2", "3ed", 3);

        List<Livro> livros = facade.buscarTodosLivros();
        ModelAndView mv;
        if (client.equals("funcionario")) {
            mv = new ModelAndView("livro/showFuncionario");
        } else {
            mv = new ModelAndView("livro/showEstudante");
        }
        mv.addObject("livros", livros);
        return mv;
    }

    @GetMapping("/livros/{livroId}/pegaremprestado")
    public ModelAndView pegarEmprestado(@PathVariable Long livroId, HttpSession session) {
        Object estudanteId = session.getAttribute("id"); 

        facade.reservarLivro(Long.parseLong(String.valueOf(estudanteId)), livroId);
        ModelAndView mv = new ModelAndView("redirect:/livros"); 
        return mv;
    }

    @GetMapping("/livros/cadastrarlivro")
    public ModelAndView showCadastroLivro(HttpSession session) {
        return new ModelAndView("/livro/cadastroLivro");
    }

    @PostMapping("/livros/cadastrarlivro")
    public ModelAndView cadastroLivro(String nome, String edicao, int quantidade, HttpSession session) {
        String res = facade.cadastrarLivro(nome, edicao, quantidade);

        if (res.equals("100")) {
            return new ModelAndView("redirect:/funcionario");
        } else {
            ModelAndView mv = new ModelAndView("redirect:/livros/cadastrarlivro");
            mv.addObject("error", "exist");
            return mv;
        }
    }

    @GetMapping("/livros/devolverlivro")
    public ModelAndView showDevolverLivro(HttpSession session) {
        return new ModelAndView("/livro/devolverLivro");
    }

    @GetMapping("/livros/emprestimos")
    public ModelAndView showEmprestimos(Long cpf, HttpSession session) {

        List<Emprestimo> emps = facade.buscarEmprestimoPorEstudante(cpf);
        ModelAndView mv = new ModelAndView("livro/showEmprestimos");
        mv.addObject("emprestimos", emps);
        return mv;
    }


    @GetMapping("/livros/devolver/{idEmprestimo}")
    public ModelAndView devolucao(@PathVariable Long idEmprestimo, HttpSession session) {

        Emprestimo emp = facade.buscaEmprestimoPorId(idEmprestimo);
        LocalDate today = LocalDate.now();
        LocalDate limit = emp.getDataTerminoEmprestimo();

        if (today.isAfter(limit)) {
            ModelAndView mv = new ModelAndView("redirect:/livros/pagamento/{idEmprestimo}");
            return mv;
        } else {
            facade.atualizaStatusEmprestimo(emp.getId());
            Long idLivro = emp.getIdLivro();
            facade.devolverLivro(idLivro);

            return new ModelAndView("/livros/devolucaoSucesso");
        }
    }

    @GetMapping("/livros/editalivro")
    public ModelAndView editaLivro(HttpSession session) {
        ModelAndView mv = new ModelAndView("/livro/editaLivro");
        mv.addObject("livros", facade.buscarTodosLivros());
        return mv;
    }

    @GetMapping("/livros/{livroId}/editaquantidade")
    public ModelAndView showEditaQuantidadeLivro(@PathVariable Long livroId, HttpSession session) {
        ModelAndView mv = new ModelAndView("/livro/editaQuantidadeLivro");
        mv.addObject("livroindex", livroId);

        return mv;
    }

    @PostMapping("/livros/{livroId}/editaquantidade")
    public ModelAndView editaQuantidadeLivro(@PathVariable Long livroId, int quantidade, HttpSession session) {
        System.out.println(livroId);
        System.out.println(quantidade);
        
        facade.atualizarQuantidadeLivro(livroId, quantidade);
        return new ModelAndView("redirect:/livros/editalivro");
    }

    @PostMapping("/livros/{livroId}/deletar")
    public ModelAndView editaQuantidadeLivro(@PathVariable Long livroId, HttpSession session) {

        facade.deletarLivro(livroId);
        return new ModelAndView("redirect:/livros/editalivro");
    }
}
