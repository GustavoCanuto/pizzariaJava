package br.com.pizzariagustavo;

import java.util.Scanner;

import br.com.pizzariagustavo.mock.MockProdutos;
import br.com.pizzariagustavo.models.Cliente;
import br.com.pizzariagustavo.models.Recibo;
import br.com.pizzariagustavo.service.AcompanhamentoService;
import br.com.pizzariagustavo.service.CarrinhoService;
import br.com.pizzariagustavo.service.ClienteService;
import br.com.pizzariagustavo.service.PizzaService;

public class Main {

	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		MockProdutos mock = new MockProdutos();
		ClienteService clienteService = new ClienteService();
		
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
				realizarCompra();
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

	private static void realizarCompra() {
		System.out.println("***Tela de Compra de Pizza***\n");
		ClienteService clienteService = new ClienteService();
		
		Cliente cliente = clienteService.validarCPF();
		
		if(cliente==null) return;
		
		PizzaService pizzaService = new PizzaService();
		AcompanhamentoService acompanhamentoService = new AcompanhamentoService();
		CarrinhoService carrinhoService = new CarrinhoService();
		
		Recibo recibo = new Recibo();
		recibo.setCliente(cliente);

		boolean compraFinalizada = false;

		while (!compraFinalizada) {
			exibirMenuCompra();
			int escolhaCompra = scanner.nextInt();

			switch (escolhaCompra) {
			case 1:
				pizzaService.escolherPizza(recibo);
				break;
			case 2:
				acompanhamentoService.escolherAcompanhamento(recibo);
				break;
			case 3:
				carrinhoService.verificarCarrinho(recibo);
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
	
	private static void exibirMenuCompra() {
		System.out.println("\nEscolha uma opção:\n");
		System.out.println("1. Escolher Sabor Pizza");
		System.out.println("2. Escolher Acompanhamento");
		System.out.println("3. Verificar Carrinho");
		System.out.println("4. Finalizar Compra/ Gerar Recibo");
		System.out.println("5. Cancelar Compra/ Voltar Menu Principal");
	}
	
	private static void finalizarCompra(Recibo recibo) {
		System.out.println("Recibo gerado:\n" + recibo);
	}
	
}
