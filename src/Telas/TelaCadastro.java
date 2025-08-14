package Telas;
import Modelos.ListaProdutos;
import Modelos.Produto;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class TelaCadastro extends JFrame {
    private JTextField tfCodigo, tfDescricao, tfMarca, tfEntrada, tfSaida, tfQuantidade;
    private JButton btnCadastrar;
    private ListaProdutos lista;

    public TelaCadastro(ListaProdutos lista) {
        this.lista = lista;
        setTitle("Cadastro de Produto");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel l1 = new JLabel("Código:");
        l1.setBounds(20, 20, 80, 20);
        tfCodigo = new JTextField();
        tfCodigo.setBounds(120, 20, 200, 20);

        JLabel l2 = new JLabel("Descrição:");
        l2.setBounds(20, 50, 80, 20);
        tfDescricao = new JTextField();
        tfDescricao.setBounds(120, 50, 200, 20);

        JLabel l3 = new JLabel("Marca:");
        l3.setBounds(20, 80, 80, 20);
        tfMarca = new JTextField();
        tfMarca.setBounds(120, 80, 200, 20);

        JLabel l4 = new JLabel("Valor Entrada:");
        l4.setBounds(20, 110, 100, 20);
        tfEntrada = new JTextField();
        tfEntrada.setBounds(120, 110, 200, 20);

        JLabel l5 = new JLabel("Valor Saída:");
        l5.setBounds(20, 140, 100, 20);
        tfSaida = new JTextField();
        tfSaida.setBounds(120, 140, 200, 20);

        JLabel l6 = new JLabel("Quantidade:");
        l6.setBounds(20, 170, 100, 20);
        tfQuantidade = new JTextField();
        tfQuantidade.setBounds(120, 170, 200, 20);

        btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(120, 210, 120, 30);

        add(l1); add(tfCodigo);
        add(l2); add(tfDescricao);
        add(l3); add(tfMarca);
        add(l4); add(tfEntrada);
        add(l5); add(tfSaida);
        add(l6); add(tfQuantidade);
        add(btnCadastrar);

        btnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int codigo = Integer.parseInt(tfCodigo.getText());
                String descricao = tfDescricao.getText();
                String marca = tfMarca.getText();
                double valorEntrada = Double.parseDouble(tfEntrada.getText());
                double valorSaida = Double.parseDouble(tfSaida.getText());
                int quantidade = Integer.parseInt(tfQuantidade.getText());

                Produto p = new Produto(codigo, descricao, marca, valorEntrada, valorSaida, quantidade);
                lista.adicionar(p);

                //Salvar Dados em Um Arquivo
                try (FileWriter fw = new FileWriter("produtos.txt", true)) {
                    fw.write("Codigo: " + codigo + "," + "Descrição: " + descricao + "," + "Marca: " + marca + "," + "Valor de Entrada: " + valorEntrada + "," + "Valor de Saída: " + valorSaida + "," + "Quantidade: " + quantidade + "\n");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
            }
        });
    }

    public static void main(String[] args) {
        ListaProdutos lista = new ListaProdutos();
        TelaCadastro tela = new TelaCadastro(lista);
        tela.setVisible(true);
    }
}


