package br.com.pizzariagustavo.mock;

import java.util.ArrayList;
import java.util.List;

import br.com.pizzariagustavo.models.produto.Acompanhamento;
import br.com.pizzariagustavo.models.produto.Pizza;

public class MockProdutos {

	private static List<Pizza> listaPizzas = new ArrayList<>();
	private static List<Acompanhamento> listaAcompanhamentos = new ArrayList<>();

	public void inicializarPizzas() {
		listaPizzas.add(new Pizza("Margarita", 25.0, "Queijo, Tomate e Manjericão", "Pizza tradicional italiana"));
		listaPizzas.add(new Pizza("Pepperoni", 30.0, "Pepperoni e Queijo", "Pizza com pepperoni"));
		listaPizzas.add(new Pizza("Frango com Catupiry", 28.0, "Frango e Catupiry", "Pizza com frango e catupiry"));
		listaPizzas.add(new Pizza("Calabresa", 27.0, "Calabresa e Queijo", "Pizza com calabresa"));
		listaPizzas.add(new Pizza("Quatro Queijos", 32.0, "Provolone, Mussarela, Parmesão e Gorgonzola",
				"Pizza com quatro tipos de queijo"));
		listaPizzas.add(
				new Pizza("Vegetariana", 29.0, "Tomate, Cebola, Pimentão, Azeitonas e Queijo", "Pizza vegetariana"));
		listaPizzas.add(new Pizza("Portuguesa", 31.0, "Presunto, Ovo, Cebola e Queijo", "Pizza à moda portuguesa"));

	}

	public void inicializarAcompanhamentos() {
		listaAcompanhamentos.add(new Acompanhamento("Refrigerante", 5.0));
		listaAcompanhamentos.add(new Acompanhamento("Sorvete", 10.0));
		listaAcompanhamentos.add(new Acompanhamento("Batata Frita", 8.0));

	}

	public static <T> String imprimirLista(List<T> lista) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < lista.size(); i++) {
			sb.append((i + 1)).append(". ").append(lista.get(i)).append("\n");
		}
		return sb.toString();
	}
	
	public static List<Pizza> getListaPizzas() {
		return listaPizzas;
	}

	public static List<Acompanhamento> getListaAcompanhamentos() {
		return listaAcompanhamentos;
	}
}
