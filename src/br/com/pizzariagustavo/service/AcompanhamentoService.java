package br.com.pizzariagustavo.service;



import javax.swing.JOptionPane;

import br.com.pizzariagustavo.mock.MockProdutos;
import br.com.pizzariagustavo.models.Recibo;
import br.com.pizzariagustavo.models.produto.Acompanhamento;

public class AcompanhamentoService extends AbstractCompraService {

    @Override
    protected String getMensagemEscolha() {
        return "Escolha um acompanhamento:\n\n" + MockProdutos.imprimirLista(MockProdutos.getListaAcompanhamentos()) + "\n";
    }

    @Override
    protected int getNumeroOpcoes() {
        return MockProdutos.getListaAcompanhamentos().size();
    }

    @Override
    protected void processarEscolha(Recibo recibo, int escolha) {
        Acompanhamento acompanhamentoEscolhido = MockProdutos.getListaAcompanhamentos().get(escolha - 1);
        recibo.setListaAcompanhamentoEscolhidas(acompanhamentoEscolhido);
        JOptionPane.showMessageDialog(null, "Acompanhamento escolhido: " + acompanhamentoEscolhido.getNome() + "\n");
    }
}
