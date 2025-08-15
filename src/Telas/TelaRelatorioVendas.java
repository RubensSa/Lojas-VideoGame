package Telas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TelaRelatorioVendas extends JFrame {
    private JTable tabela;
    private DefaultTableModel modelo;
    private JLabel lblTotal;

    public TelaRelatorioVendas() {
        setTitle("Relatório de Vendas");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        String[] colunas = {"Data/Hora", "Código", "Descrição", "Quantidade", "Valor Total"};
        modelo = new DefaultTableModel(colunas, 0);
        tabela = new JTable(modelo);

        lblTotal = new JLabel("Total Vendido: R$ 0.00");
        lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);

        add(new JScrollPane(tabela), BorderLayout.CENTER);
        add(lblTotal, BorderLayout.SOUTH);

        carregarVendas();
    }

    private void carregarVendas() {
        double total = 0;
        modelo.setRowCount(0);

        try (BufferedReader br = new BufferedReader(new FileReader("vendas.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");
                if (partes.length == 5) {
                    String dataHora = partes[0].trim();
                    String codigo = partes[1].trim();
                    String descricao = partes[2].trim();
                    String qtd = partes[3].trim();
                    String valor = partes[4].trim();
                    total += Double.parseDouble(valor);
                    modelo.addRow(new Object[]{dataHora, codigo, descricao, qtd, valor});
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar vendas: " + e.getMessage());
        }

        lblTotal.setText(String.format("Total Vendido: R$ %.2f", total));
    }
}