package Modelos;

public class ListaProdutos {
    private Produto inicio;
    private Produto[] produtos;

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
    public Produto buscarPorDescricao(String desc) {
        for (Produto p : produtos) {
            if (p.getDescricao().equalsIgnoreCase(desc)) return p;
        }
        return null;
    }
    public Produto buscarPorCodigo(int codigo) {
        Produto atual = inicio;
        while (atual != null) {
            if (atual.codigo == codigo) {
                return atual;
            }
            atual = atual.prox;
        }
        return null;
    }
    public boolean editarProduto(int codigo, String novaDescricao, String novaMarca,
                                 double novoValorEntrada, double novoValorSaida, int novaQuantidade) {
        Produto p = buscarPorCodigo(codigo);
        if (p != null) {
            p.descricao = novaDescricao;
            p.marca = novaMarca;
            p.valorEntrada = novoValorEntrada;
            p.valorSaida = novoValorSaida;
            p.quantidade = novaQuantidade;
            return true;
        }
        return false;
    }
    public boolean reporEstoque(int codigo, int quantidade) {
        Produto p = buscarPorCodigo(codigo);
        if (p != null) {
            p.quantidade += quantidade;
            return true;
        }
        return false;
    }
    public boolean venderProduto(int codigo, int quantidade) {
        Produto p = buscarPorCodigo(codigo);
        if (p != null && p.quantidade >= quantidade) {
            p.quantidade -= quantidade;
            // Aqui você pode registrar no relatório de vendas
            return true;
        }
        return false;
    }
    public void alterarPrecos(double percentual) {
        Produto atual = inicio;
        while (atual != null) {
            atual.valorEntrada += atual.valorEntrada * percentual / 100;
            atual.valorSaida += atual.valorSaida * percentual / 100;
            atual = atual.prox;
        }
    }
    public void ordenarPorDescricao() {
        inicio = mergeSort(inicio);
    }
    private Produto mergeSort(Produto head) {
        if (head == null || head.prox == null) return head;
        Produto meio = getMiddle(head);
        Produto meioProx = meio.prox;
        meio.prox = null;

        Produto esquerda = mergeSort(head);
        Produto direita = mergeSort(meioProx);

        return merge(esquerda, direita);
    }
    private Produto merge(Produto a, Produto b) {
        if (a == null) return b;
        if (b == null) return a;

        if (a.descricao.compareToIgnoreCase(b.descricao) <= 0) {
            a.prox = merge(a.prox, b);
            return a;
        } else {
            b.prox = merge(a, b.prox);
            return b;
        }
    }
    private Produto getMiddle(Produto head) {
        if (head == null) return head;
        Produto lento = head, rapido = head.prox;
        while (rapido != null && rapido.prox != null) {
            lento = lento.prox;
            rapido = rapido.prox.prox;
        }
        return lento;
    }
    public Produto getInicio() {
        return inicio;
    }
}
