package Modelos;

import javax.swing.*;

public class ListaProdutos {
    private Produto inicio;

    public ListaProdutos() {
        this.inicio = null;
    }

    public void limpar() {
        inicio = null;
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

    private int cmp(String a, String b) {
        if (a == null && b == null) return 0;
        if (a == null) return 1;
        if (b == null) return -1;
        return a.compareToIgnoreCase(b);
    }

    private Produto merge(Produto a, Produto b) {
        if (a == null) return b;
        if (b == null) return a;

        if (cmp(a.descricao, b.descricao) <= 0) {
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