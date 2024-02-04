package br.com.pizzariagustavo.service;

import java.util.Scanner;

import br.com.pizzariagustavo.models.Recibo;
import br.com.pizzariagustavo.models.produto.Acompanhamento;
import br.com.pizzariagustavo.models.produto.Pizza;

public class CarrinhoService {
	
	private Scanner scanner = new Scanner(System.in);
	
	public void verificarCarrinho(Recibo recibo) {
		
		System.out.println("\n****Itens no Carrinho****");
		int i = 0;
		for (Pizza pizza : recibo.getListaPizzasEscolhidas()) {
			System.out.println("Pizza: " + ++i + " " + pizza.getNome());
		}
		int j = 0;
		for (Acompanhamento acompanhamento : recibo.getListaAcompanhamentoEscolhidas()) {
			System.out.println("Acompanhamento: " + ++j + " " + acompanhamento.getNome());
		}

		System.out.println("Escolha uma opção:\n");
		System.out.println("1. Remover Pizza");
		System.out.println("2. Remover Acompanhamento");
		System.out.println("3. Voltar");

		int escolhaRemover = scanner.nextInt();

		switch (escolhaRemover) {
		case 1:

			System.out.println("Escolha o número da pizza a ser removida:");
			int numeroPizzaRemover = scanner.nextInt();

			if (numeroPizzaRemover >= 1 && numeroPizzaRemover <= recibo.getListaPizzasEscolhidas().size()) {
				Pizza pizzaRemover = recibo.getListaPizzasEscolhidas().get(numeroPizzaRemover - 1);
				recibo.removerPizza(pizzaRemover);
				System.out.println("Pizza removida: " + pizzaRemover.getNome());
			} else {
				System.out.println("Opção inválida. Tente novamente.");
			}
			break;

		case 2:

			System.out.println("Escolha o número do acompanhamento a ser removido:");
			int numeroAcompanhamentoRemover = scanner.nextInt();

			if (numeroAcompanhamentoRemover >= 1
					&& numeroAcompanhamentoRemover <= recibo.getListaAcompanhamentoEscolhidas().size()) {
				Acompanhamento acompanhamentoRemover = recibo.getListaAcompanhamentoEscolhidas()
						.get(numeroAcompanhamentoRemover - 1);
				recibo.removerAcompanhamento(acompanhamentoRemover);
				System.out.println("Acompanhamento removido: " + acompanhamentoRemover.getNome());
			} else {
				System.out.println("Opção inválida. Tente novamente.");
			}

			break;

		case 3:

			System.out.println("Voltando ao Menu Principal.");

			break;

		default:
			System.out.println("Opção inválida. Tente novamente.");

		}
	}
}
