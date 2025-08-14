package Modelos;

public class Produto {

         public int codigo;
         public String descricao;
         public String marca;
         public double valorEntrada;
         public double valorSaida;
         public int quantidade;
         public Produto prox; // ponteiro para o próximo produto
         public double preco;
         public Produto inicio;

    public Produto(int codigo, String descricao, String marca, double valorEntrada, double valorSaida, int quantidade) {
            this.codigo = codigo;
            this.descricao = descricao;
            this.marca = marca;
            this.valorEntrada = valorEntrada;
            this.valorSaida = valorSaida;
            this.quantidade = quantidade;
            this.prox = null;

        }
    public int getCodigo() { return codigo; }
    public String getDescricao() { return descricao; }
    public String getMarca() { return marca; }
    public double getPreco() { return preco; }
    public int getQuantidade() { return (int) quantidade; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setMarca(String marca) { this.marca = marca; }
    public void setPreco(double preco) { this.preco = preco; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
    public String toString() {
        return codigo + " - " + descricao + " (" + marca + ") - R$" + preco + " - Estoque: " + quantidade;
    }
    public Produto getInicio() {
        return inicio;
    }

    public void setInicio(Produto inicio) {
        this.inicio = inicio;
    }
}







