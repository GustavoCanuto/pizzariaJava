package br.com.pizzariagustavo.models.produto;

public class Acompanhamento extends Produto {
	
	 public Acompanhamento(String nome, double valor) {
	        super(nome, valor);
	    }

	@Override
	public String toString() {
		return "" + nome + " - valor: " + valor+"" ;
	}

	

}
