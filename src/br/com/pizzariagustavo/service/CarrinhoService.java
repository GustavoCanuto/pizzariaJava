package br.com.pizzariagustavo.service;

import java.util.List;

import javax.swing.JOptionPane;

import br.com.pizzariagustavo.exceptions.OpcaoInvalida;
import br.com.pizzariagustavo.exceptions.OperacaoCanceladaException;
import br.com.pizzariagustavo.models.Recibo;
import br.com.pizzariagustavo.models.produto.Acompanhamento;
import br.com.pizzariagustavo.models.produto.Pizza;

public class CarrinhoService {

	public void verificarCarrinho(Recibo recibo) {

		String escolhaRemoverInput = JOptionPane.showInputDialog(null, exibirMenuCarrinho(recibo));

		if (escolhaRemoverInput == null)
			return;
		
		try {
			int escolhaRemover = Integer.parseInt(escolhaRemoverInput);

			switch (escolhaRemover) {
			case 1:

				int numeroPizzaRemover = removerProduto(recibo.getListaPizzasEscolhidas(),
						"Escolha o número da pizza a ser removida:");
				Pizza pizzaRemover = recibo.getListaPizzasEscolhidas().get(numeroPizzaRemover - 1);
				recibo.removerPizza(pizzaRemover);
				JOptionPane.showMessageDialog(null, "Pizza removida: " + pizzaRemover.getNome());
				break;

			case 2:

				int numeroAcompanhamentoRemover = removerProduto(recibo.getListaAcompanhamentoEscolhidas(),
						"Escolha o número do acompanhamento a ser removido:");

				Acompanhamento acompanhamentoRemover = recibo.getListaAcompanhamentoEscolhidas()
						.get(numeroAcompanhamentoRemover - 1);
				recibo.removerAcompanhamento(acompanhamentoRemover);
				JOptionPane.showMessageDialog(null, "Acompanhamento removido: " + acompanhamentoRemover.getNome());

				break;

			case 3:
				JOptionPane.showMessageDialog(null, "Voltando ao Menu Principal.");
				break;

			default:
				JOptionPane.showMessageDialog(null, "Opção inválida. Tente novamente.");
			}
		} catch (OperacaoCanceladaException e) {

			JOptionPane.showMessageDialog(null, "Operação no carrinho cancelada.");

		} catch (OpcaoInvalida e) {

			JOptionPane.showMessageDialog(null, "Opção para ser removida inválida.");
		} catch (NumberFormatException e) {

			JOptionPane.showMessageDialog(null, "Opção inválida. Digite um número.");

		}
	}

	private <T> int removerProduto(List<T> listaProdutoEscolhido, String mensagem) {

		String numeroPizzaRemoverInput = JOptionPane.showInputDialog(null, mensagem);

		if (numeroPizzaRemoverInput == null) {
			throw new OperacaoCanceladaException();
		}

		int numeroPizzaRemover = Integer.parseInt(numeroPizzaRemoverInput);

		if (numeroPizzaRemover >= 1 && numeroPizzaRemover <= listaProdutoEscolhido.size()) {
			return numeroPizzaRemover;

		} else {
			JOptionPane.showMessageDialog(null, "Opção inválida. Tente novamente.");
			throw new OpcaoInvalida();
		}
	}

	private String exibirMenuCarrinho(Recibo recibo) {
		StringBuilder carrinhoInfo = new StringBuilder("**** Itens no Carrinho ****\n");

		int i = 0;
		for (var pizza : recibo.getListaPizzasEscolhidas()) {
			carrinhoInfo.append("Pizza ").append(++i).append(" - ").append(pizza.getNome()).append("\n");
		}

		carrinhoInfo.append("\n");

		int j = 0;
		for (var acompanhamento : recibo.getListaAcompanhamentoEscolhidas()) {
			carrinhoInfo.append("Acompanhamento ").append(++j).append(" - ").append(acompanhamento.getNome())
					.append("\n");
		}

		carrinhoInfo.append("\nEscolha uma opção:\n\n");
		carrinhoInfo.append("1. Remover Pizza\n");
		carrinhoInfo.append("2. Remover Acompanhamento\n");
		carrinhoInfo.append("3. Voltar\n\n");

		return carrinhoInfo.toString();
	}

}
