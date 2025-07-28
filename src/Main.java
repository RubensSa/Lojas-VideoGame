import Modelos.ListaProdutos;
import Telas.TelaCadastro;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ListaProdutos lista = new ListaProdutos();
        TelaCadastro tela = new TelaCadastro(lista);
        tela.setVisible(true);

        }
}