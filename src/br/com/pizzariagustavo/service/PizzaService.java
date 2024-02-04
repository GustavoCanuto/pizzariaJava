package br.com.pizzariagustavo.service;

import java.util.List;
import java.util.Scanner;

import br.com.pizzariagustavo.mock.MockCliente;
import br.com.pizzariagustavo.mock.MockProdutos;
import br.com.pizzariagustavo.models.Cliente;
import br.com.pizzariagustavo.models.Recibo;
import br.com.pizzariagustavo.models.produto.Acompanhamento;
import br.com.pizzariagustavo.models.produto.Pizza;

public class PizzaService {
	
	private Scanner scanner = new Scanner(System.in);
	
	public void comprarPizza() {
		
		System.out.println("***Tela de Compra de Pizza***\n");
		System.out.println("Digite o CPF do cliente:");
		String cpfClienteCompra = scanner.next();

		Cliente clienteEncontrado = encontrarClientePorCPF(cpfClienteCompra);

		if (clienteEncontrado != null) {
			realizarCompra(clienteEncontrado);
		} else {
			System.out.println("Cliente não encontrado. Voltando ao Menu Principal.\n");
		}
	}
	
	private  Cliente encontrarClientePorCPF(String cpf) {
		for (Cliente cliente : MockCliente.getListaClientes()) {
			if (cliente.getCpf().equals(cpf)) {
				return cliente;
			}
		}
		return null;
	}
	

	private void realizarCompra(Cliente cliente) {
		Recibo recibo = new Recibo();
		recibo.setCliente(cliente);

		boolean compraFinalizada = false;

		while (!compraFinalizada) {
			exibirMenuCompra();
			int escolhaCompra = scanner.nextInt();

			switch (escolhaCompra) {
			case 1:
				escolherPizza(recibo);
				break;
			case 2:
				escolherAcompanhamento(recibo);
				break;
			case 3:
				verificarCarrinho(recibo);
				break;
			case 4:
				finalizarCompra(recibo);
				compraFinalizada = true;
				break;
			case 5:
				System.out.println("\nVoltando ao Menu Principal.");
				compraFinalizada = true;
				break;
			default:
				System.out.println("\nOpção inválida. Tente novamente.");
			}
		}
	}
	

	private void escolherPizza(Recibo recibo) {
		Pizza pizzaEscolhida = null;
		boolean finalizarCompraPizza = false;

		while (!finalizarCompraPizza) {
			int escolhaSabor = 0;
			int escolhaSabor2 = 0;

			System.out.println("\n1- Pizza Completa\n2- Pizza Meio a Meio ");

			int tipoPizza = scanner.nextInt();

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

			} else if (tipoPizza != 1 && tipoPizza != 2) {
				System.out.println("\nOpcao Invalida");
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

	private void escolherAcompanhamento(Recibo recibo) {
		boolean finalizarCompraAcompanhamento = false;

		while (!finalizarCompraAcompanhamento) {
			System.out.println("\nEscolha um acompanhamento:\n");

			imprimirLista(MockProdutos.getListaAcompanhamentos());

			int escolhaAcompanhamento = scanner.nextInt();

			if (escolhaAcompanhamento >= 1 && escolhaAcompanhamento <= MockProdutos.getListaAcompanhamentos().size()) {

				Acompanhamento acompanhamentoEscolhido = MockProdutos.getListaAcompanhamentos().get(escolhaAcompanhamento - 1);

				recibo.setListaAcompanhamentoEscolhidas(acompanhamentoEscolhido);

				System.out.println("Acompanhamento escolhido: " + acompanhamentoEscolhido.getNome());

				System.out.println("\nDeseja adicionar outro Acompanhamento?\n1- Sim \n2- Não ");

				int adicionarOutroAcompanhamento = scanner.nextInt();

				if (adicionarOutroAcompanhamento == 2) {

					finalizarCompraAcompanhamento = true;

				}
			} else {
				System.out.println("\nOpção inválida. Tente novamente.");
			}
		}

	}
	


	private void verificarCarrinho(Recibo recibo) {
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

	private void exibirMenuCompra() {
		System.out.println("\nEscolha uma opção:\n");
		System.out.println("1. Escolher Sabor Pizza");
		System.out.println("2. Escolher Acompanhamento");
		System.out.println("3. Verificar Carrinho");
		System.out.println("4. Finalizar Compra/ Gerar Recibo");
		System.out.println("5. Cancelar Compra/ Voltar Menu Principal");
	}
	
	private  void finalizarCompra(Recibo recibo) {
		System.out.println("Recibo gerado:\n" + recibo);
	}
	
	private  <T> void imprimirLista(List<T> lista) {
		for (int i = 0; i < lista.size(); i++) {
			System.out.println((i + 1) + ". " + lista.get(i));
		}
	}

}
