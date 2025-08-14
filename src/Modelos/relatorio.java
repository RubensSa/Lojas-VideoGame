package Modelos;

public class relatorio {
    private Produto inicio;

    public void relatorioEstoque() {
        Produto atual = inicio;
        System.out.println("===== RELATÓRIO DE ESTOQUE =====");
        while (atual != null) {
            System.out.println(atual.descricao + " - Quantidade: " + atual.quantidade);
            atual = atual.prox;
        }
    }

}
