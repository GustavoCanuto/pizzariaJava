package br.com.pizzariagustavo.models;

public class Cliente {

	private String nome;
	private String cpf;
	private String logradouro;
	private String numero;

	public Cliente(String nome, String cpf, String logradouro, String numero) {
		this.nome = nome;
		this.cpf = cpf;
		this.logradouro = logradouro;
		this.numero = numero;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String toString() {
		return "\n******* Informações do Cliente *******\n\n" + "nome: " + nome + '\n' + "cpf: " + cpf + '\n'
				+ "logradouro: " + logradouro + '\n' + "numero: " + numero + '\n';
	}

}
