package br.com.pizzariagustavo.service;

import javax.swing.JOptionPane;

import br.com.pizzariagustavo.exceptions.OperacaoCanceladaException;
import br.com.pizzariagustavo.mock.MockCliente;
import br.com.pizzariagustavo.models.Cliente;

public class ClienteService {

	public void cadastrarCliente() {
		try {
			String nomeCliente = obterInputNaoNulo("Digite o nome do cliente:");
			String cpfCliente = obterInputNaoNulo("Digite o CPF do cliente:");
			String logradouroCliente = obterInputNaoNulo("Digite o logradouro do cliente:");
			String numeroCliente = obterInputNaoNulo("Digite o número do cliente:");

			Cliente novoCliente = new Cliente(nomeCliente, cpfCliente, logradouroCliente, numeroCliente);
			MockCliente.setListaClientes(novoCliente);

			JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso\n" + novoCliente + "\n");
		} catch (OperacaoCanceladaException e) {
			JOptionPane.showMessageDialog(null, "Operação cancelada. Voltando ao Menu Principal.");
		}
	}

	public Cliente validarCPF() {
		try {
			String cpfClienteCompra = obterInputNaoNulo("Digite o CPF do cliente:");

			Cliente clienteEncontrado = encontrarClientePorCPF(cpfClienteCompra);

			if (clienteEncontrado != null) {
				return clienteEncontrado;
			} else {
				JOptionPane.showMessageDialog(null, "Cliente não encontrado. Voltando ao Menu Principal.\n");
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "CPF inválido. Digite apenas números.");
		} catch (OperacaoCanceladaException e) {
			JOptionPane.showMessageDialog(null, "Operação cancelada. Voltando ao Menu Principal.");
		}

		return null;
	}

	private Cliente encontrarClientePorCPF(String cpf) {
		for (Cliente cliente : MockCliente.getListaClientes()) {
			if (cliente.getCpf().equals(cpf)) {
				return cliente;
			}
		}
		return null;
	}

	private String obterInputNaoNulo(String mensagem) {
		String input = JOptionPane.showInputDialog(mensagem);

		if (input == null) {

			throw new OperacaoCanceladaException();
		}

		while (input.trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Campo não pode estar em branco. Tente novamente.");
			input = JOptionPane.showInputDialog(mensagem);

			if (input == null) {

				throw new OperacaoCanceladaException();
			}
		}

		return input;
	}
}
