package br.com.pizzariagustavo.models;

public class Acompanhamento {
	
	private String nome;
	private double valor;

	
	public Acompanhamento(String nome, double valor) {
		this.nome = nome;
		this.valor = valor;
	}


	public String getNome() {
		return nome;
	}


	@Override
	public String toString() {
		return "nome=" + nome + "- valor=" + valor ;
	}

	

}
