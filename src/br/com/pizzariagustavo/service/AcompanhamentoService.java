package br.com.pizzariagustavo.service;

import java.util.List;

import javax.swing.JOptionPane;

import br.com.pizzariagustavo.mock.MockProdutos;
import br.com.pizzariagustavo.models.Recibo;
import br.com.pizzariagustavo.models.produto.Acompanhamento;

public class AcompanhamentoService {

    public void escolherAcompanhamento(Recibo recibo) {

        boolean finalizarCompraAcompanhamento = false;

        while (!finalizarCompraAcompanhamento) {
            String escolhaAcompanhamentoInput = JOptionPane.showInputDialog(null,
                    "Escolha um acompanhamento:\n\n" + imprimirLista(MockProdutos.getListaAcompanhamentos()) + "\n");

            if (escolhaAcompanhamentoInput == null) {
                JOptionPane.showMessageDialog(null, "Escolha de acompanhamento cancelada.");
                return;
            }

            try {
                int escolhaAcompanhamento = Integer.parseInt(escolhaAcompanhamentoInput);

                if (escolhaAcompanhamento >= 1
                        && escolhaAcompanhamento <= MockProdutos.getListaAcompanhamentos().size()) {

                    Acompanhamento acompanhamentoEscolhido = MockProdutos.getListaAcompanhamentos()
                            .get(escolhaAcompanhamento - 1);

                    recibo.setListaAcompanhamentoEscolhidas(acompanhamentoEscolhido);

                    JOptionPane.showMessageDialog(null,
                            "Acompanhamento escolhido: " + acompanhamentoEscolhido.getNome() + "\n");

                    int adicionarOutroAcompanhamento = JOptionPane.showConfirmDialog(null,
                            "Deseja adicionar outro acompanhamento?\n", "Confirmação", JOptionPane.YES_NO_OPTION);

                    if (adicionarOutroAcompanhamento == JOptionPane.NO_OPTION) {
                        finalizarCompraAcompanhamento = true;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Opção inválida. Tente novamente.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Opção inválida. Digite um número.");
            }
        }
    }

    private <T> String imprimirLista(List<T> lista) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lista.size(); i++) {
            sb.append((i + 1)).append(". ").append(lista.get(i)).append("\n");
        }
        return sb.toString();
    }
}
