package br.com.pizzariagustavo;

import java.util.Scanner;

import javax.swing.JOptionPane;

import br.com.pizzariagustavo.mock.MockProdutos;
import br.com.pizzariagustavo.models.Cliente;
import br.com.pizzariagustavo.models.Recibo;
import br.com.pizzariagustavo.service.AcompanhamentoService;
import br.com.pizzariagustavo.service.CarrinhoService;
import br.com.pizzariagustavo.service.ClienteService;
import br.com.pizzariagustavo.service.PizzaService;

public class Main {

    // Singleton para Scanner
    private static class ScannerSingleton {
        private static final Scanner INSTANCE = new Scanner(System.in);

        private ScannerSingleton() {}

        public static Scanner getInstance() {
            return INSTANCE;
        }
    }

    public static void main(String[] args) {

        MockProdutos mock = new MockProdutos();
        ClienteService clienteService = new ClienteService();

        mock.inicializarPizzas();
        mock.inicializarAcompanhamentos();

        boolean sair = false;

        while (!sair) {
            int escolha = exibirMenuPrincipal();

            switch (escolha) {
                case 1:
                    clienteService.cadastrarCliente();
                    break;
                case 2:
                    realizarCompra();
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "Saindo do sistema");
                    sair = true;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida. Tente novamente.");
            }
        }

        ScannerSingleton.getInstance().close();
    }

    private static void realizarCompra() {
        ClienteService clienteService = new ClienteService();

        Cliente cliente = clienteService.validarCPF();

        if (cliente == null)
            return;

        PizzaService pizzaService = new PizzaService();
        AcompanhamentoService acompanhamentoService = new AcompanhamentoService();
        CarrinhoService carrinhoService = new CarrinhoService();

        Recibo recibo = new Recibo();
        recibo.setCliente(cliente);

        boolean compraFinalizada = false;

        while (!compraFinalizada) {

            int escolhaCompra = exibirMenuCompra();

            switch (escolhaCompra) {
                case 1:
                    pizzaService.realizarCompra(recibo);
                    break;
                case 2:
                    acompanhamentoService.realizarCompra(recibo);
                    break;
                case 3:
                    carrinhoService.verificarCarrinho(recibo);
                    break;
                case 4:
                    finalizarCompra(recibo);
                    compraFinalizada = true;
                    break;
                case 5:
                    JOptionPane.showMessageDialog(null, "Voltando ao Menu Principal.");
                    compraFinalizada = true;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida. Tente novamente.");
            }
        }
    }

    private static int exibirMenuPrincipal() {
        try {
            String input = JOptionPane.showInputDialog("""
                    **** Bem-Vindo a Pizzaria Gustavo ****

                    Escolha uma opção:

                     1. Cadastro de Cliente
                     2. Compra de Pizza
                     3. Sair

                    """);

            if (input == null) {
                JOptionPane.showMessageDialog(null, "Saindo do sistema");
                System.exit(0);
            }
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Opção inválida. Tente novamente.");
            return exibirMenuPrincipal();
        }
    }

    private static int exibirMenuCompra() {
        try {
            String input = JOptionPane.showInputDialog("""
                    Escolha uma opção:

                    1. Escolher Sabor Pizza
                    2. Escolher Acompanhamento
                    3. Verificar Carrinho
                    4. Finalizar Compra/ Gerar Recibo
                    5. Cancelar Compra/ Voltar Menu Principal

                    """);

            if (input == null) {
                return 5;
            }
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Opção inválida. Tente novamente.");
            return exibirMenuCompra();
        }
    }

    private static void finalizarCompra(Recibo recibo) {
    	JOptionPane.showMessageDialog(null, "Recibo gerado:\n" + recibo);
    }
}
