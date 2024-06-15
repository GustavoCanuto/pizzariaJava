package br.com.pizzariagustavo.service;

import java.util.List;

import javax.swing.JOptionPane;

import br.com.pizzariagustavo.models.Recibo;

public abstract class AbstractCompraService {

    public final void realizarCompra(Recibo recibo) {
        boolean finalizarCompra = false;

        while (!finalizarCompra) {
            String escolhaInput = JOptionPane.showInputDialog(null, getMensagemEscolha());

            if (escolhaInput == null) {
                JOptionPane.showMessageDialog(null, "Operação cancelada.");
                return;
            }

            try {
                int escolha = Integer.parseInt(escolhaInput);

                if (escolha >= 1 && escolha <= getNumeroOpcoes()) {
                    processarEscolha(recibo, escolha);

                    int adicionarOutro = JOptionPane.showConfirmDialog(null,
                            "Deseja adicionar outro?\n", "Confirmação", JOptionPane.YES_NO_OPTION);

                    if (adicionarOutro == JOptionPane.NO_OPTION) {
                        finalizarCompra = true;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Opção inválida.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Opção inválida. Digite um número.");
            }
        }
    }

    protected abstract String getMensagemEscolha();

    protected abstract int getNumeroOpcoes();

    protected abstract void processarEscolha(Recibo recibo, int escolha);
}