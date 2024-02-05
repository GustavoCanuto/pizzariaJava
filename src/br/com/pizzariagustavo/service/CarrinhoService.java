package br.com.pizzariagustavo.service;

import javax.swing.JOptionPane;

import br.com.pizzariagustavo.models.Recibo;
import br.com.pizzariagustavo.models.produto.Acompanhamento;
import br.com.pizzariagustavo.models.produto.Pizza;

public class CarrinhoService {

	public void verificarCarrinho(Recibo recibo) {

		StringBuilder carrinhoInfo = new StringBuilder("**** Itens no Carrinho ****");

		int i = 0;
		for (Pizza pizza : recibo.getListaPizzasEscolhidas()) {
			carrinhoInfo.append("Pizza ").append(++i).append(" - ").append(pizza.getNome()).append("\n");
		}

		carrinhoInfo.append("\n");

		int j = 0;
		for (Acompanhamento acompanhamento : recibo.getListaAcompanhamentoEscolhidas()) {
			carrinhoInfo.append("Acompanhamento ").append(++j).append(" - ").append(acompanhamento.getNome())
					.append("\n");
		}

		carrinhoInfo.append("\nEscolha uma opção:\n\n");
		carrinhoInfo.append("1. Remover Pizza\n");
		carrinhoInfo.append("2. Remover Acompanhamento\n");
		carrinhoInfo.append("3. Voltar\n\n");

		String escolhaRemoverInput = JOptionPane.showInputDialog(null, carrinhoInfo.toString());

		if (escolhaRemoverInput == null) {
			JOptionPane.showMessageDialog(null, "Operação no carrinho cancelada.");
			return;
		}

		try {
			int escolhaRemover = Integer.parseInt(escolhaRemoverInput);

			switch (escolhaRemover) {
			case 1:
				String numeroPizzaRemoverInput = JOptionPane.showInputDialog(null,
						"Escolha o número da pizza a ser removida:");

				if (numeroPizzaRemoverInput == null) {
					JOptionPane.showMessageDialog(null, "Operação no carrinho cancelada.");
					return;
				}

				int numeroPizzaRemover = Integer.parseInt(numeroPizzaRemoverInput);

				if (numeroPizzaRemover >= 1 && numeroPizzaRemover <= recibo.getListaPizzasEscolhidas().size()) {
					Pizza pizzaRemover = recibo.getListaPizzasEscolhidas().get(numeroPizzaRemover - 1);
					recibo.removerPizza(pizzaRemover);
					JOptionPane.showMessageDialog(null, "Pizza removida: " + pizzaRemover.getNome());
				} else {
					JOptionPane.showMessageDialog(null, "Opção inválida. Tente novamente.");
				}
				break;

			case 2:
				String numeroAcompanhamentoRemoverInput = JOptionPane.showInputDialog(null,
						"Escolha o número do acompanhamento a ser removido:");

				if (numeroAcompanhamentoRemoverInput == null) {
					JOptionPane.showMessageDialog(null, "Operação no carrinho cancelada.");
					return;
				}

				int numeroAcompanhamentoRemover = Integer.parseInt(numeroAcompanhamentoRemoverInput);

				if (numeroAcompanhamentoRemover >= 1
						&& numeroAcompanhamentoRemover <= recibo.getListaAcompanhamentoEscolhidas().size()) {
					Acompanhamento acompanhamentoRemover = recibo.getListaAcompanhamentoEscolhidas()
							.get(numeroAcompanhamentoRemover - 1);
					recibo.removerAcompanhamento(acompanhamentoRemover);
					JOptionPane.showMessageDialog(null, "Acompanhamento removido: " + acompanhamentoRemover.getNome());
				} else {
					JOptionPane.showMessageDialog(null, "Opção inválida. Tente novamente.");
				}
				break;

			case 3:
				JOptionPane.showMessageDialog(null, "Voltando ao Menu Principal.");
				break;

			default:
				JOptionPane.showMessageDialog(null, "Opção inválida. Tente novamente.");
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Opção inválida. Digite um número.");
		}
	}
}
