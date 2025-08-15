package Telas;

import Modelos.ListaProdutos;
import Modelos.Produto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TelaGerenciarProdutos extends JFrame {
    private JTable tabela;
    private DefaultTableModel modeloTabela;
    private ListaProdutos lista;

    public TelaGerenciarProdutos(ListaProdutos lista) {
        this.lista = lista;

        setTitle("Gerenciar Produtos");
        setSize(800, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        String[] colunas = {"Código", "Descrição", "Marca", "Valor Entrada", "Valor Saída", "Quantidade"};
        modeloTabela = new DefaultTableModel(colunas, 0);
        tabela = new JTable(modeloTabela);

        JScrollPane scrollPane = new JScrollPane(tabela);
        add(scrollPane, BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel(new FlowLayout());
        JButton btnEditar = new JButton("Editar");
        JButton btnRepor = new JButton("Repor Estoque");
        JButton btnVender = new JButton("Vender Produto");
        JButton btnAlterarPreco = new JButton("Alterar Preços (%)");
        JButton btnOrdenar = new JButton("Ordenar por Descrição");
        JButton btnRecarregar = new JButton("Recarregar");

        painelBotoes.add(btnEditar);
        painelBotoes.add(btnRepor);
        painelBotoes.add(btnVender);
        painelBotoes.add(btnAlterarPreco);
        painelBotoes.add(btnOrdenar);
        painelBotoes.add(btnRecarregar);

        add(painelBotoes, BorderLayout.SOUTH);

        btnRecarregar.addActionListener(e -> carregarDadosArquivo());

        btnEditar.addActionListener(e -> editarProduto());
        btnRepor.addActionListener(e -> reporEstoque());
        btnVender.addActionListener(e -> venderProduto());
        btnAlterarPreco.addActionListener(e -> alterarPrecos());
        btnOrdenar.addActionListener(e -> {
            lista.ordenarPorDescricao();
            carregarDadosLista();
        });

        carregarDadosArquivo();
    }

    private void carregarDadosArquivo() {
        modeloTabela.setRowCount(0);
        try (BufferedReader br = new BufferedReader(new FileReader("produtos.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");
                if (partes.length == 6) {
                    String codigo = partes[0].split(":")[1].trim();
                    String descricao = partes[1].split(":")[1].trim();
                    String marca = partes[2].split(":")[1].trim();
                    String valorEntrada = partes[3].split(":")[1].trim();
                    String valorSaida = partes[4].split(":")[1].trim();
                    String quantidade = partes[5].split(":")[1].trim();

                    Produto p = new Produto(Integer.parseInt(codigo), descricao, marca,
                            Double.parseDouble(valorEntrada), Double.parseDouble(valorSaida),
                            Integer.parseInt(quantidade));

                    lista.adicionar(p);

                    modeloTabela.addRow(new Object[]{codigo, descricao, marca, valorEntrada, valorSaida, quantidade});
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao ler arquivo: " + e.getMessage());
        }
    }

    private void carregarDadosLista() {
        modeloTabela.setRowCount(0);
        Produto atual = lista.getInicio();
        while (atual != null) {
            modeloTabela.addRow(new Object[]{
                    atual.codigo,
                    atual.descricao,
                    atual.marca,
                    atual.valorEntrada,
                    atual.valorSaida,
                    atual.quantidade
            });
            atual = atual.prox;
        }
    }

    private void editarProduto() {
        int row = tabela.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um produto para editar.");
            return;
        }
        int codigo = Integer.parseInt(tabela.getValueAt(row, 0).toString());
        String novaDesc = JOptionPane.showInputDialog("Nova descrição:");
        String novaMarca = JOptionPane.showInputDialog("Nova marca:");
        double novoEntrada = Double.parseDouble(JOptionPane.showInputDialog("Novo valor de entrada:"));
        double novoSaida = Double.parseDouble(JOptionPane.showInputDialog("Novo valor de saída:"));
        int novaQtd = Integer.parseInt(JOptionPane.showInputDialog("Nova quantidade:"));

        if (lista.editarProduto(codigo, novaDesc, novaMarca, novoEntrada, novoSaida, novaQtd)) {
            carregarDadosLista();
            JOptionPane.showMessageDialog(this, "Produto editado com sucesso!");
        }
    }

    private void reporEstoque() {
        int row = tabela.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um produto.");
            return;
        }
        int codigo = Integer.parseInt(tabela.getValueAt(row, 0).toString());
        int qtd = Integer.parseInt(JOptionPane.showInputDialog("Quantidade para repor:"));

        if (lista.reporEstoque(codigo, qtd)) {
            carregarDadosLista();
            JOptionPane.showMessageDialog(this, "Estoque atualizado!");
        }
    }

    private void venderProduto() {
        int row = tabela.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um produto.");
            return;
        }
        int codigo = Integer.parseInt(tabela.getValueAt(row, 0).toString());
        int qtd = Integer.parseInt(JOptionPane.showInputDialog("Quantidade para vender:"));

        if (lista.venderProduto(codigo, qtd)) {
            carregarDadosLista();
            JOptionPane.showMessageDialog(this, "Venda realizada!");
        } else {
            JOptionPane.showMessageDialog(this, "Quantidade insuficiente.");
        }
    }

    private void alterarPrecos() {
        double perc = Double.parseDouble(JOptionPane.showInputDialog("Percentual de aumento/redução (%):"));
        lista.alterarPrecos(perc);
        carregarDadosLista();
        JOptionPane.showMessageDialog(this, "Preços atualizados!");
    }
    private void carregarTabela() {
        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.setRowCount(0);

        Produto atual = lista.getInicio();
        while (atual != null) {
            modelo.addRow(new Object[]{
                    atual.codigo,
                    atual.descricao,
                    atual.marca,
                    atual.valorEntrada,
                    atual.valorSaida,
                    atual.quantidade
            });
            atual = atual.prox;
        }
    }
}