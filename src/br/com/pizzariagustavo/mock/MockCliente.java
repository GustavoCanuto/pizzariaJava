package br.com.pizzariagustavo.mock;

import java.util.ArrayList;
import java.util.List;

import br.com.pizzariagustavo.models.Cliente;

public class MockCliente {
	
	private static List<Cliente> listaClientes = new ArrayList<>();

	public static List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public static void setListaClientes(Cliente novoCliente) {
		MockCliente.listaClientes.add(novoCliente);
	}

}
