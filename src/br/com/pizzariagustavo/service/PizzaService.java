package br.com.pizzariagustavo.service;

import javax.swing.JOptionPane;

import br.com.pizzariagustavo.exceptions.OpcaoInvalida;
import br.com.pizzariagustavo.exceptions.OperacaoCanceladaException;
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

			if (tipoPizzaInput == null) {
				finalizarCompraPizza = true;
				continue;
			}

			try {
				int tipoPizza = Integer.parseInt(tipoPizzaInput);

				if (tipoPizza != 1 && tipoPizza != 2)
					throw new OpcaoInvalida();

				escolhaSabor = gerarCardapio("Escolha o sabor de pizza:");

				pizzaEscolhida = MockProdutos.getListaPizzas().get(escolhaSabor - 1);

				if (tipoPizza == 2) {

					escolhaSabor2 = gerarCardapio("Escolha outro sabor de pizza:");

					pizzaEscolhida = pizzaEscolhida
							.gerarPizzaMista(MockProdutos.getListaPizzas().get(escolhaSabor2 - 1));
					recibo.setListaPizzasEscolhidas(pizzaEscolhida);

				} else {

					recibo.setListaPizzasEscolhidas(MockProdutos.getListaPizzas().get(escolhaSabor - 1));
				}

				JOptionPane.showMessageDialog(null, "Pizza escolhida: " + pizzaEscolhida.getNome());

				int adicionarOutraPizza = JOptionPane.showConfirmDialog(null, "Deseja adicionar outra pizza?",
						"Confirmação", JOptionPane.YES_NO_OPTION);

				if (adicionarOutraPizza == JOptionPane.NO_OPTION) {
					finalizarCompraPizza = true;
				}

			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Opção inválida. Digite um número.");

			} catch (OperacaoCanceladaException e) {
				finalizarCompraPizza = true;
				JOptionPane.showMessageDialog(null, "Cancelando escolha de pizza...");

			} catch (OpcaoInvalida e) {
				JOptionPane.showMessageDialog(null, "Opção inválida.");

			}
		}
	}

	private int gerarCardapio(String mensagem) {

		String escolhaSaborInput = JOptionPane.showInputDialog(null,
				mensagem + "\n\n" + MockProdutos.imprimirLista(MockProdutos.getListaPizzas()) + "\n");

		if (escolhaSaborInput == null) {
			throw new OperacaoCanceladaException();
		}

		int escolhaSabor = Integer.parseInt(escolhaSaborInput);

		if (escolhaSabor < 1 || escolhaSabor > MockProdutos.getListaPizzas().size()) {
			throw new OpcaoInvalida();
		}

		return escolhaSabor;
	}
}
