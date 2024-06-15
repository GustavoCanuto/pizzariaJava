package br.com.pizzariagustavo.service;

import javax.swing.JOptionPane;

import br.com.pizzariagustavo.exceptions.OpcaoInvalida;
import br.com.pizzariagustavo.exceptions.OperacaoCanceladaException;
import br.com.pizzariagustavo.mock.MockProdutos;
import br.com.pizzariagustavo.models.Recibo;
import br.com.pizzariagustavo.models.produto.Pizza;

public class PizzaService extends AbstractCompraService {

    private Pizza pizzaEscolhida = null;

    @Override
    protected String getMensagemEscolha() {
        return "Escolha o tipo de pizza:\n\n1. Pizza Completa\n2. Pizza Meio a Meio\n\n";
    }

    @Override
    protected int getNumeroOpcoes() {
        return 2;
    }

    @Override
    protected void processarEscolha(Recibo recibo, int tipoPizza) {
        int escolhaSabor = gerarCardapio("Escolha o sabor de pizza:");

        pizzaEscolhida = MockProdutos.getListaPizzas().get(escolhaSabor - 1);

        if (tipoPizza == 2) {
            int escolhaSabor2 = gerarCardapio("Escolha outro sabor de pizza:");
            pizzaEscolhida = pizzaEscolhida.gerarPizzaMista(MockProdutos.getListaPizzas().get(escolhaSabor2 - 1));
        }

        recibo.setListaPizzasEscolhidas(pizzaEscolhida);
        JOptionPane.showMessageDialog(null, "Pizza escolhida: " + pizzaEscolhida.getNome());
    }

    private int gerarCardapio(String mensagem) {
        String escolhaSaborInput = JOptionPane.showInputDialog(null,
                mensagem + "\n\n" + MockProdutos.imprimirLista(MockProdutos.getListaPizzas()) + "\n");

        if (escolhaSaborInput == null) {
            throw new OperacaoCanceladaException();
        }

        int escolhaSabor = Integer.parseInt(escolhaSaborInput);

        if (escolhaSabor < 1 || escolhaSabor > MockProdutos.getListaPizzas().size()) {
            throw new OpcaoInvalida();
        }

        return escolhaSabor;
    }
}
