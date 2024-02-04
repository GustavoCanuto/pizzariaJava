package br.com.pizzariagustavo.models.produto;

public class Pizza extends Produto{
	
	private String sabor;
	private String descricao;
	
	public Pizza(String nome, double valor, String sabor, String descricao) {
		super(nome, valor);
		this.sabor = sabor;
		this.descricao = descricao;
	}

	public String getSabor() {
		return sabor;
	}

	public String getDescricao() {
		return descricao;
	}

	public Pizza gerarPizzaMista(Pizza segundoSabor) {
	    String novoNome = "Metade " + this.getNome() + " e " + segundoSabor.getNome();
	    double novoValor = Math.max(this.getValor(), segundoSabor.getValor());
	    String novoSabor = "metade é " + this.getSabor() + " e outra metade é " + segundoSabor.getSabor();
	    String novaDescricao = "Pizza meio a meio";
	    return new Pizza(novoNome, novoValor, novoSabor, novaDescricao);
	}
	
	
	@Override
	public String toString() {
		return "nome: " + nome + ", valor: " + valor + ", sabor: " + sabor + "";
	}
	
}
