package Telas;

import Modelos.ListaProdutos;
import Modelos.Produto;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TelaRelatorioEstoque extends JFrame {
    private JTable tabela;
    private DefaultTableModel modelo;
    public TelaRelatorioEstoque(ListaProdutos lista) {
        setTitle("Relatório de Estoque");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        String[] colunas = {"Código", "Descrição", "Quantidade"};
        modelo = new DefaultTableModel(colunas, 0);
        tabela = new JTable(modelo);

        add(new JScrollPane(tabela), BorderLayout.CENTER);

        carregarEstoque(lista);
    }
    private void carregarEstoque(ListaProdutos lista) {
        modelo.setRowCount(0);
        Produto atual = lista.getInicio();
        while (atual != null) {
            modelo.addRow(new Object[]{atual.codigo, atual.descricao, atual.quantidade});
            atual = atual.prox;
        }
    }
}