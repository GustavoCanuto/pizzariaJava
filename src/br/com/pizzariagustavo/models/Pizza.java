package br.com.pizzariagustavo.models;

public class Pizza {
	

	private String nome;
	private double valor;
	private String sabor;
	private String descricao;
	
	public Pizza(String nome, double valor, String sabor, String descricao) {
		this.nome = nome;
		this.valor = valor;
		this.sabor = sabor;
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public double getValor() {
		return valor;
	}

	public String getSabor() {
		return sabor;
	}

	public String getDescricao() {
		return descricao;
	}

	public Pizza gerarPizzaMista(Pizza segundoSabor) {
		String novoNome = this.getNome()+" e "+segundoSabor.getNome();
		double novoValor = (this.getValor() > segundoSabor.getValor()) ? this.getValor() : segundoSabor.getValor();
		String novoSabor = "Meio "+this.getSabor()+" e Meio "+segundoSabor.getSabor();
		String novaDescricao = "Pizza meio a meio";
		return new Pizza(novoNome,novoValor,novoSabor,novaDescricao);
	}
	
	
	@Override
	public String toString() {
		return "Pizza [nome=" + nome + ", valor=" + valor + ", sabor=" + sabor + "]";
	}
	
}
