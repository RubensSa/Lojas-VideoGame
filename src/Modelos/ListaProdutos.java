package Modelos;

public class ListaProdutos {
    private Produto inicio;

    public ListaProdutos() {
        this.inicio = null;
    }

    public void adicionar(Produto novo) {
        if (inicio == null) {
            inicio = novo;
        } else {
            Produto atual = inicio;
            while (atual.prox != null) {
                atual = atual.prox;
            }
            atual.prox = novo;
        }
    }
    // Adicionar Produtos Lista Encadeada
    public void listar() {
        Produto atual = inicio;
        while (atual != null) {
            System.out.println("Código: " + atual.codigo);
            System.out.println("Descrição: " + atual.descricao);
            System.out.println("Marca: " + atual.marca);
            System.out.println("Valor Entrada: R$" + atual.valorEntrada);
            System.out.println("Valor Saída: R$" + atual.valorSaida);
            System.out.println("Quantidade: " + atual.quantidade);
            System.out.println("----------------------");
            atual = atual.prox;
        }
    }

    public void showProduct() {

    }
}


