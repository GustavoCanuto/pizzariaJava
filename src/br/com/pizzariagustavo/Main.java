package br.com.pizzariagustavo;

import java.util.ArrayList;
import java.util.Scanner;

import br.com.pizzariagustavo.models.Acompanhamento;
import br.com.pizzariagustavo.models.Cliente;
import br.com.pizzariagustavo.models.Pizza;
import br.com.pizzariagustavo.models.Recibo;

public class Main {

	public static void main(String[] args) {

		ArrayList<Pizza> listaPizzas = new ArrayList<>();
		ArrayList<Acompanhamento> listaAcompanhamentos = new ArrayList<>();
		ArrayList<Cliente> listaClientes = new ArrayList<>();

		listaPizzas.add(new Pizza("Margarita", 25.0, "Queijo, Tomate e Manjericão", "Pizza tradicional italiana"));
		listaPizzas.add(new Pizza("Pepperoni", 30.0, "Pepperoni e Queijo", "Pizza com pepperoni"));
		listaPizzas.add(new Pizza("Frango com Catupiry", 28.0, "Frango e Catupiry", "Pizza com frango e catupiry"));
		listaPizzas.add(new Pizza("Calabresa", 27.0, "Calabresa e Queijo", "Pizza com calabresa"));
		listaPizzas.add(new Pizza("Quatro Queijos", 32.0, "Provolone, Mussarela, Parmesão e Gorgonzola",
				"Pizza com quatro tipos de queijo"));
		listaPizzas.add(
				new Pizza("Vegetariana", 29.0, "Tomate, Cebola, Pimentão, Azeitonas e Queijo", "Pizza vegetariana"));
		listaPizzas.add(new Pizza("Portuguesa", 31.0, "Presunto, Ovo, Cebola e Queijo", "Pizza à moda portuguesa"));

		listaAcompanhamentos.add(new Acompanhamento("Refrigerante", 5.0));
		listaAcompanhamentos.add(new Acompanhamento("Sorvete", 10.0));
		listaAcompanhamentos.add(new Acompanhamento("Batata Frita", 8.0));

		Scanner scanner = new Scanner(System.in);

		boolean sair = false;

		while (!sair) {

			System.out.println("**** Bem-Vindo a Pizzaria Gustavo ***\nEscolha uma opção:\n");
			System.out.println("1. Cadastro de cliente");
			System.out.println("2. Compra de Pizza");
			System.out.println("3. Sair");

			int escolha = scanner.nextInt();

			switch (escolha) {
			case 1:
				System.out.println("***Tela de Cadastro do Cliente***\n");
				System.out.println("Digite o nome do cliente:");
				String nomeCliente = scanner.next();
				System.out.println("Digite o CPF do cliente:");
				String cpfCliente = scanner.next();
				System.out.println("Digite o logradouro do cliente:");
				String logradouroCliente = scanner.next();
				System.out.println("Digite o número do cliente:");
				String numeroCliente = scanner.next();

				Cliente novoCliente = new Cliente(nomeCliente, cpfCliente, logradouroCliente, numeroCliente);
				listaClientes.add(novoCliente);

				System.out.println("Cliente cadastrado com sucesso:\n" + novoCliente);

				break;

			case 2:

				System.out.println("***Tela de Compra de Pizza***\n");
				System.out.println("Digite o CPF do cliente:");
				String cpfClienteCompra = scanner.next();

				// Verificar se o cliente existe
				Cliente clienteEncontrado = null;
				for (Cliente cliente : listaClientes) {
					if (cliente.getCpf().equals(cpfClienteCompra)) {
						clienteEncontrado = cliente;
						break;
					}
				}

				boolean compraFinalizada = false;
				Recibo recibo = new Recibo();
				if (clienteEncontrado != null) {

					recibo.setCliente(clienteEncontrado);
					while (!compraFinalizada) {

						// Continuar com as opções de compra de pizza
						System.out.println("Escolha uma opção:\n");
						System.out.println("1. Escolher Sabor Pizza");
						System.out.println("2. Escolher Acompanhamento");
						System.out.println("3. Verificar Carrinho"); // adicionar opcao de excluir produto
						System.out.println("4. Finalizar Compra/ Gerar Recibo");
						System.out.println("5. Cancelar Compra/ Voltar Menu Principal");

						int escolhaCompra = scanner.nextInt();
						Pizza pizzaEscolhida = null;

						switch (escolhaCompra) {
						case 1:
							boolean finalizarCompraPizza = false;

							while (!finalizarCompraPizza) {
								int escolhaSabor = 0;
								int escolhaSabor2 = 0;
								// Listar sabores disponíveis
								System.out.println("\n1 - Pizza Completa \n2- Pizza Meio a Meio ");

								int tipoPizza = scanner.nextInt();

								if (tipoPizza == 1) {
									System.out.println("Escolha um sabor de pizza:");

									for (int i = 0; i < listaPizzas.size(); i++) {
										System.out.println((i + 1) + ". " + listaPizzas.get(i).getNome() + " - "
												+ listaPizzas.get(i).getValor());
									}
									escolhaSabor = scanner.nextInt();

								}

								else if (tipoPizza == 2) {
									System.out.println("Escolha o primeiro sabor");

									for (int i = 0; i < listaPizzas.size(); i++) {
										System.out.println((i + 1) + ". " + listaPizzas.get(i).getNome() + " - "
												+ listaPizzas.get(i).getValor());
									}
									escolhaSabor = scanner.nextInt();
									if (escolhaSabor >= 1 && escolhaSabor <= listaPizzas.size()) {
										pizzaEscolhida = listaPizzas.get(escolhaSabor - 1);
									}

									System.out.println("Escolha o segundoSabor sabor");
									for (int i = 0; i < listaPizzas.size(); i++) {
										System.out.println((i + 1) + ". " + listaPizzas.get(i).getNome() + " - "
												+ listaPizzas.get(i).getValor());
									}
									escolhaSabor2 = scanner.nextInt();

									if (escolhaSabor2 >= 1 && escolhaSabor2 <= listaPizzas.size()) {
										pizzaEscolhida = pizzaEscolhida
												.gerarPizzaMista(listaPizzas.get(escolhaSabor2 - 1));
										recibo.setListaPizzasEscolhidas(pizzaEscolhida);
									}

								} else {
									System.out.println("Opcao Invalida");
								}

								if (escolhaSabor >= 1 && escolhaSabor <= listaPizzas.size()) {
									if (escolhaSabor2 == 0) {
										pizzaEscolhida = listaPizzas.get(escolhaSabor - 1);
										recibo.setListaPizzasEscolhidas(pizzaEscolhida);
									}
									System.out.println("Pizza escolhida: " + pizzaEscolhida.getNome());
									System.out.println("\nDeseja adicionar outra Pizza?\n 1 - Sim \n 2- Não ");
									int adicionarOutraPizza = scanner.nextInt();
									if (adicionarOutraPizza == 2) {
										finalizarCompraPizza = true;
									}
								} else {
									System.out.println("Opção inválida. Tente novamente.");
								}
							}
							break;

						case 2:
							boolean finalizarCompraAcompanhamento = false;

							while (!finalizarCompraAcompanhamento) {
								System.out.println("Escolha um acompanhamento:");

								for (int i = 0; i < listaAcompanhamentos.size(); i++) {
									System.out.println((i + 1) + ". " + listaAcompanhamentos.get(i));
								}

								int escolhaAcompanhamento = scanner.nextInt();

								if (escolhaAcompanhamento >= 1
										&& escolhaAcompanhamento <= listaAcompanhamentos.size()) {
									Acompanhamento acompanhamentoEscolhido = listaAcompanhamentos
											.get(escolhaAcompanhamento - 1);
									recibo.setListaAcompanhamentoEscolhidas(acompanhamentoEscolhido);
									System.out
											.println("Acompanhamento escolhido: " + acompanhamentoEscolhido.getNome());
									System.out.println("\nDeseja adicionar outro Acompanhamento?\n 1 - Sim \n 2- Não ");
									int adicionarOutroAcompanhamento = scanner.nextInt();
									if (adicionarOutroAcompanhamento == 2) {
										finalizarCompraAcompanhamento = true;
									}
								} else {
									System.out.println("Opção inválida. Tente novamente.");
								}
							}

							break;

						case 3:
							// Exibir itens escolhidos
							System.out.println("****Itens no Carrinho:***");
							int i = 0;
							for (Pizza pizza : recibo.getListaPizzasEscolhidas()) {
								System.out.println("Pizza: " + ++i + " " + pizza.getNome());
							}
							int j = 0;
							for (Acompanhamento acompanhamento : recibo.getListaAcompanhamentoEscolhidas()) {
								System.out.println("Acompanhamento: " + ++j + " " + acompanhamento.getNome());
							}

							// Dar a opção de remover itens
							System.out.println("Escolha uma opção:\n");
							System.out.println("1. Remover Pizza");
							System.out.println("2. Remover Acompanhamento");
							System.out.println("3. Voltar");

							int escolhaRemover = scanner.nextInt();

							switch (escolhaRemover) {
							case 1:

								System.out.println("Escolha o número da pizza a ser removida:");
								int numeroPizzaRemover = scanner.nextInt();

								if (numeroPizzaRemover >= 1
										&& numeroPizzaRemover <= recibo.getListaPizzasEscolhidas().size()) {
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

								if (numeroAcompanhamentoRemover >= 1 && numeroAcompanhamentoRemover <= recibo
										.getListaAcompanhamentoEscolhidas().size()) {
									Acompanhamento acompanhamentoRemover = recibo.getListaAcompanhamentoEscolhidas()
											.get(numeroAcompanhamentoRemover - 1);
									recibo.removerAcompanhamento(acompanhamentoRemover);
									System.out.println("Acompanhamento removido: " + acompanhamentoRemover.getNome());
								} else {
									System.out.println("Opção inválida. Tente novamente.");
								}

								break;

							case 3:

								System.out.println("Compra cancelada. Voltando ao Menu Principal.");
								
								break;

							default:
								System.out.println("Opção inválida. Tente novamente.");

							}
							break;
						case 4:
						
							System.out.println("Recibo gerado:\n" + recibo);
							compraFinalizada = true;
							
							break;
						case 5:
							System.out.println("Voltando ao Menu Principal.");
							compraFinalizada = true;
							break;
						default:
							System.out.println("Opção inválida. Tente novamente.");
						}
					}
				}
				break;
			case 3:
				System.out.println("Saindo do sistesma");
				sair = true;
				break;

			default:
				System.out.println("Opção inválida. Tente novamente.");
			}

		}
		scanner.close();
	}

}
