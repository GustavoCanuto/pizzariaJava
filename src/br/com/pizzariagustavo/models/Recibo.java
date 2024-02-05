package br.com.pizzariagustavo.models;

import java.util.ArrayList;
import java.util.List;

import br.com.pizzariagustavo.models.produto.Acompanhamento;
import br.com.pizzariagustavo.models.produto.Pizza;

public class Recibo {

	private static int contadorId = 1;
	private int idCompra;
	private Cliente cliente;
	private ArrayList<Pizza> listaPizzasEscolhidas = new ArrayList<>();
	private ArrayList<Acompanhamento> listaAcompanhamentoEscolhidas = new ArrayList<>();

	public Recibo() {
		this.idCompra = contadorId++;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setListaPizzasEscolhidas(Pizza pizza) {
		this.listaPizzasEscolhidas.add(pizza);
	}

	public void setListaAcompanhamentoEscolhidas(Acompanhamento acompanhamento) {
		this.listaAcompanhamentoEscolhidas.add(acompanhamento);
	}

	public void removerPizza(Pizza pizza) {
		this.listaPizzasEscolhidas.remove(pizza);
	}

	public void removerAcompanhamento(Acompanhamento acompanhamento) {
		this.listaAcompanhamentoEscolhidas.remove(acompanhamento);
	}

	public List<Pizza> getListaPizzasEscolhidas() {
		return listaPizzasEscolhidas;
	}

	public List<Acompanhamento> getListaAcompanhamentoEscolhidas() {
		return listaAcompanhamentoEscolhidas;
	}


	public String toString() {
		StringBuilder result = new StringBuilder(
				"\nCodigo da compra: " + idCompra + "\n " + cliente + "\n******* Informações da Compra *******\n");

		result.append("\nLista Pizzas Compradas\n");
		for (Pizza pizza : listaPizzasEscolhidas) {
			result.append(pizza.toString());
			result.append("\n");
		}

		result.append("\nLista Acompanhamentos Escolhidos\n");
		for (Acompanhamento acompanhamento : listaAcompanhamentoEscolhidas) {
			result.append(acompanhamento.toString());
			result.append("\n");
		}

		result.append("\n");

		return result.toString();
	}

}
