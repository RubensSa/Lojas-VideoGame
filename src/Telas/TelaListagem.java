package Telas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TelaListagem extends JFrame {
    private JTable tabela;
    private DefaultTableModel modeloTabela;

    public TelaListagem() {
        setTitle("Lista de Produtos");
        setSize(700, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Configuração das colunas da tabela
        String[] colunas = {"Código", "Descrição", "Marca", "Valor Entrada", "Valor Saída", "Quantidade"};
        modeloTabela = new DefaultTableModel(colunas, 0);
        tabela = new JTable(modeloTabela);

        // Adiciona a tabela com barra de rolagem
        JScrollPane scrollPane = new JScrollPane(tabela);
        add(scrollPane, BorderLayout.CENTER);

        // Botão para recarregar os dados do arquivo
        JButton btnRecarregar = new JButton("Recarregar");
        btnRecarregar.addActionListener(e -> carregarDados());
        add(btnRecarregar, BorderLayout.SOUTH);

        carregarDados();
    }

    private void carregarDados() {
        modeloTabela.setRowCount(0); // limpa a tabela

        try (BufferedReader br = new BufferedReader(new FileReader("produtos.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                // Exemplo do formato no arquivo:
                // Codigo: 1,Descrição: Produto X,Marca: Y,Valor de Entrada: 10.5,Valor de Saída: 15.0,Quantidade: 20
                String[] partes = linha.split(",");
                if (partes.length == 6) {
                    String codigo = partes[0].split(":")[1].trim();
                    String descricao = partes[1].split(":")[1].trim();
                    String marca = partes[2].split(":")[1].trim();
                    String valorEntrada = partes[3].split(":")[1].trim();
                    String valorSaida = partes[4].split(":")[1].trim();
                    String quantidade = partes[5].split(":")[1].trim();

                    modeloTabela.addRow(new Object[]{
                            codigo, descricao, marca, valorEntrada, valorSaida, quantidade
                    });
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}