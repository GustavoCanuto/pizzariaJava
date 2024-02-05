package br.com.pizzariagustavo.service;

import javax.swing.JOptionPane;

import br.com.pizzariagustavo.mock.MockCliente;
import br.com.pizzariagustavo.models.Cliente;

public class ClienteService {

	public void cadastrarCliente() {
		String nomeCliente = JOptionPane.showInputDialog("Digite o nome do cliente:\n");
		String cpfCliente = JOptionPane.showInputDialog("Digite o CPF do cliente:\n");
		String logradouroCliente = JOptionPane.showInputDialog("Digite o logradouro do cliente:\n");
		String numeroCliente = JOptionPane.showInputDialog("Digite o número do cliente:\n");

		Cliente novoCliente = new Cliente(nomeCliente, cpfCliente, logradouroCliente, numeroCliente);
		MockCliente.setListaClientes(novoCliente);

		JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso\n" + novoCliente + "\n");
	}

	public Cliente validarCPF() {
		String cpfClienteCompra = JOptionPane.showInputDialog("Digite o CPF do cliente:\n");

		Cliente clienteEncontrado = encontrarClientePorCPF(cpfClienteCompra);

		if (clienteEncontrado != null) {
			return clienteEncontrado;
		} else {
			JOptionPane.showMessageDialog(null, "Cliente não encontrado. Voltando ao Menu Principal.\n");
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

}
