package Arquitetura;

import Diagrama de classe.CadastroEstudanteControle;
import Diagrama de classe.CadastroLivroControle;
import Diagrama de classe.DevolucaoControle;
import Diagrama de classe.LoginEstudanteControle;
import Diagrama de classe.EmprestimoControle;
import Diagrama de classe.Estudante;
import Diagrama de classe.Livro;
import Casos de uso.Estudante;

public class Fachada {

	public boolean consultarEstudante(estudante : Estudante);

	private CadastroEstudanteControle cadastroEstudanteControle;

	private CadastroLivroControle cadastroLivroControle;

	private DevolucaoControle devolucaoControle;

	private LoginEstudanteControle loginEstudanteControle;

	private EmprestimoControle emprestimoControle;

	private LoginEstudanteControle loginEstudanteControle;

	private CadastroEstudanteControle cadastroEstudanteControle;

	private EmprestimoControle emprestimoControle;

	private DevolucaoControle devolucaoControle;

	public void cadastrarEstudante(Estudante estudante) {

	}

	public void redirecionarLogin() {

	}

	public void cadastrarLivro(Livro livro) {

	}

	public void redirecionarTelaSistema() {

	}

	public void devolverLivro(Livro livro, Estudante estudante) {

	}

	public void emitirBoletoMulta(Estudante estudante) {

	}

	public boolean vefiricarDataEmprestimo(Livro livro) {
		return false;
	}

	public void loginEstudante(str cpf, str senha) {

	}

	public void logOutEstudante() {

	}

	public boolean verificarDisponibilidadeLivro(Livro livro) {
		return false;
	}

	public void pegarLivroEmprestado(Livro livro, Estudante estudante, str data) {

	}

	public void gerarComprovante(Livro livro, Estudante estudante, str data) {

	}

	public void atualizarEstoqueLivro(Livro livro) {

	}

	public boolean verificarDisponibilidadeLivro(Livro livro) {
		return false;
	}

	public void pegarLivroEmprestado(Livro livro, Estudante estudante, str data) {

	}

	public void gerarComprovante(Livro livro, Estudante estudante, str data) {

	}

}
