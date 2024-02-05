package br.com.pizzariagustavo.service;

import java.util.List;

import javax.swing.JOptionPane;

import br.com.pizzariagustavo.mock.MockProdutos;
import br.com.pizzariagustavo.models.Recibo;
import br.com.pizzariagustavo.models.produto.Pizza;

public class PizzaService {

	public void escolherPizza(Recibo recibo) {

		Pizza pizzaEscolhida = null;
		boolean finalizarCompraPizza = false;

		while (!finalizarCompraPizza) {
			int escolhaSabor = 0;
			int escolhaSabor2 = 0;

			String tipoPizzaInput = JOptionPane.showInputDialog(null,
					"Escolha o tipo de pizza:\n\n1. Pizza Completa\n2. Pizza Meio a Meio\n\n");

			int tipoPizza = Integer.parseInt(tipoPizzaInput);

			if (tipoPizza != 1 && tipoPizza != 2) {
				JOptionPane.showMessageDialog(null, "Opção inválida");
				return;
			}

			String escolhaSaborInput = JOptionPane.showInputDialog(null,
					"Escolha o sabor de pizza:\n\n" + imprimirLista(MockProdutos.getListaPizzas()) + "\n");

			escolhaSabor = Integer.parseInt(escolhaSaborInput);

			if (escolhaSabor >= 1 && escolhaSabor <= MockProdutos.getListaPizzas().size()) {
				pizzaEscolhida = MockProdutos.getListaPizzas().get(escolhaSabor - 1);
			}

			if (tipoPizza == 2) {

				String escolhaSaborInput2 = JOptionPane.showInputDialog(null,
						"Escolha outro sabor de pizza:\n\n" + imprimirLista(MockProdutos.getListaPizzas()) + "\n");

				escolhaSabor2 = Integer.parseInt(escolhaSaborInput2);

				if (escolhaSabor2 >= 1 && escolhaSabor2 <= MockProdutos.getListaPizzas().size()) {
					pizzaEscolhida = pizzaEscolhida
							.gerarPizzaMista(MockProdutos.getListaPizzas().get(escolhaSabor2 - 1));
					recibo.setListaPizzasEscolhidas(pizzaEscolhida);
				}

			}

			if (escolhaSabor >= 1 && escolhaSabor <= MockProdutos.getListaPizzas().size()) {

				if (escolhaSabor2 == 0) {
					pizzaEscolhida = MockProdutos.getListaPizzas().get(escolhaSabor - 1);
					recibo.setListaPizzasEscolhidas(pizzaEscolhida);
				}

				JOptionPane.showMessageDialog(null, "Pizza escolhida: " + pizzaEscolhida.getNome());
				int adicionarOutraPizza = JOptionPane.showConfirmDialog(null, "Deseja adicionar outra pizza?",
						"Confirmação", JOptionPane.YES_NO_OPTION);

				if (adicionarOutraPizza == JOptionPane.NO_OPTION) {
					finalizarCompraPizza = true;
				}
			} else {
				JOptionPane.showMessageDialog(null, "Opção inválida. Tente novamente.");
			}
		}
	}

	private String imprimirLista(List<Pizza> lista) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < lista.size(); i++) {
			sb.append((i + 1)).append(". ").append(lista.get(i)).append("\n");
		}
		return sb.toString();
	}

}
