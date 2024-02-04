package br.com.pizzariagustavo.service;

import java.util.Scanner;

import br.com.pizzariagustavo.mock.MockCliente;
import br.com.pizzariagustavo.models.Cliente;

public class ClienteService {
	
	private Scanner scanner = new Scanner(System.in);
	
	public void cadastrarCliente() {
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
		MockCliente.setListaClientes(novoCliente);


		System.out.println("Cliente cadastrado com sucesso:\n" + novoCliente);
	}

}
