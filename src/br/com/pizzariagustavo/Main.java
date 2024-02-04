package br.com.pizzariagustavo;

import java.util.Scanner;

import br.com.pizzariagustavo.mock.MockProdutos;
import br.com.pizzariagustavo.service.ClienteService;
import br.com.pizzariagustavo.service.PizzaService;

public class Main {

	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		MockProdutos mock = new MockProdutos();
		ClienteService clienteService = new ClienteService();
		PizzaService pizzaService = new PizzaService();
		
		mock.inicializarPizzas();
		mock.inicializarAcompanhamentos();
		
		boolean sair = false;

		while (!sair) {
			exibirMenuPrincipal();
			int escolha = scanner.nextInt();

			switch (escolha) {
			case 1:
				clienteService.cadastrarCliente();
				break;
			case 2:
				pizzaService.comprarPizza();
				break;
			case 3:
				System.out.println("\nSaindo do sistema");
				sair = true;
				break;
			default:
				System.out.println("\nOpção inválida. Tente novamente.");
			}
		}

		scanner.close();
	}

	

	private static void exibirMenuPrincipal() {
		System.out.println("**** Bem-Vindo a Pizzaria Gustavo ***\n\nEscolha uma opção:\n");
		System.out.println("1. Cadastro de cliente");
		System.out.println("2. Compra de Pizza");
		System.out.println("3. Sair\n");
	}



}
