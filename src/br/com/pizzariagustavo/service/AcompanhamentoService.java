package br.com.pizzariagustavo.service;

import java.util.List;
import java.util.Scanner;

import br.com.pizzariagustavo.mock.MockProdutos;
import br.com.pizzariagustavo.models.Recibo;
import br.com.pizzariagustavo.models.produto.Acompanhamento;

public class AcompanhamentoService {

	private Scanner scanner = new Scanner(System.in);
	
	public void escolherAcompanhamento(Recibo recibo) {
		
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
	
	private  <T> void imprimirLista(List<T> lista) {
		for (int i = 0; i < lista.size(); i++) {
			System.out.println((i + 1) + ". " + lista.get(i));
		}
	}

	
}
