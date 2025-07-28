package Modelos;

public class Produto {
         int codigo;
         String descricao;
         String marca;
         double valorEntrada;
         double valorSaida;
         int quantidade;
        Produto prox; // ponteiro para o próximo produto
        public Produto(int codigo, String descricao, String marca, double valorEntrada, double valorSaida, int quantidade) {
            this.codigo = codigo;
            this.descricao = descricao;
            this.marca = marca;
            this.valorEntrada = valorEntrada;
            this.valorSaida = valorSaida;
            this.quantidade = quantidade;
            this.prox = null;

        }

}

