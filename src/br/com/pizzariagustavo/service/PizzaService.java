package br.com.pizzariagustavo.service;

import java.util.List;
import java.util.Scanner;

import br.com.pizzariagustavo.mock.MockProdutos;
import br.com.pizzariagustavo.models.Recibo;
import br.com.pizzariagustavo.models.produto.Pizza;

public class PizzaService {
	
	private Scanner scanner = new Scanner(System.in);

	public void escolherPizza(Recibo recibo) {
		
		Pizza pizzaEscolhida = null;
		boolean finalizarCompraPizza = false;

		while (!finalizarCompraPizza) {
			int escolhaSabor = 0;
			int escolhaSabor2 = 0;

			System.out.println("\n1- Pizza Completa\n2- Pizza Meio a Meio ");

			int tipoPizza = scanner.nextInt();
			
			if (tipoPizza != 1 && tipoPizza != 2) {
				System.out.println("\nOpcao Invalida");
				return;
			}

			System.out.println("\nEscolha um sabor de pizza:");

			imprimirLista(MockProdutos.getListaPizzas());

			escolhaSabor = scanner.nextInt();

			if (escolhaSabor >= 1 && escolhaSabor <= MockProdutos.getListaPizzas().size()) {
				pizzaEscolhida = MockProdutos.getListaPizzas().get(escolhaSabor - 1);
			}

			if (tipoPizza == 2) {

				System.out.println("Escolha o segundo sabor");

				imprimirLista(MockProdutos.getListaPizzas());

				escolhaSabor2 = scanner.nextInt();

				if (escolhaSabor2 >= 1 && escolhaSabor2 <= MockProdutos.getListaPizzas().size()) {
					pizzaEscolhida = pizzaEscolhida.gerarPizzaMista(MockProdutos.getListaPizzas().get(escolhaSabor2 - 1));
					recibo.setListaPizzasEscolhidas(pizzaEscolhida);
				}

			}  

			if (escolhaSabor >= 1 && escolhaSabor <= MockProdutos.getListaPizzas().size()) {

				if (escolhaSabor2 == 0) {
					pizzaEscolhida = MockProdutos.getListaPizzas().get(escolhaSabor - 1);
					recibo.setListaPizzasEscolhidas(pizzaEscolhida);
				}

				System.out.println("Pizza escolhida: " + pizzaEscolhida.getNome());
				System.out.println("\nDeseja adicionar outra Pizza?\n1- Sim \n2- Não ");
				int adicionarOutraPizza = scanner.nextInt();

				if (adicionarOutraPizza == 2) {
					finalizarCompraPizza = true;
				}

			} else {

				System.out.println("\nOpção inválida. Tente novamente.");

			}
		}
	}


	private  <T> void imprimirLista(List<T> lista) {
		for (int i = 0; i < lista.size(); i++) {
			System.out.println((i + 1) + ". " + lista.get(i));
		}
	}


}
