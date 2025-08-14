import Modelos.ListaProdutos;
import Telas.TelaCadastro;
import Telas.TelaGerenciarProdutos;
import Telas.TelaListagem;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ListaProdutos lista = new ListaProdutos();
        TelaCadastro tela = new TelaCadastro(lista);
        tela.setVisible(true);
//
//        SwingUtilities.invokeLater(() -> {
//            TelaListagem telaListagem = new TelaListagem();
//            telaListagem.setVisible(true);
//        });
        ListaProdutos telagerenciar = new ListaProdutos();
        new TelaGerenciarProdutos(lista).setVisible(true);

        }

}