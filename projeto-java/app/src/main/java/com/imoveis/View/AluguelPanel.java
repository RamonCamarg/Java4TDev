package com.imoveis.View;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.imoveis.Connection.AluguelDAO;
import com.imoveis.Model.Aluguel;

public class AluguelPanel extends JPanel {
    private JPanel formPanel, buttonPanel, tablePanel;
    private JButton realizaVenda, cancelaVenda;
    private JTextField inputProduto, inputQuantidade, inputTotal, inputCliente, inputData;
    private DefaultTableModel tableModel;
    private JTable table;
    private List<Aluguel> alugueis;
    private JScrollPane jSPane;

    public AluguelPanel() {
        super();
        alugueis = new ArrayList<>();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Organização vertical

        // Título
        JPanel title = new JPanel(new FlowLayout());
        title.add(new JLabel("Alugueis Registrados"));
        add(title);

        // Painel de Formulário (campos de entrada)
        formPanel = new JPanel(new GridLayout(3, 2, 10, 10)); // GridLayout para organizar os campos
        formPanel.add(new JLabel("Produto:"));
        inputProduto = new JTextField(10);
        formPanel.add(inputProduto);

        formPanel.add(new JLabel("Quantidade:"));
        inputQuantidade = new JTextField(10);
        formPanel.add(inputQuantidade);

        formPanel.add(new JLabel("Total:"));
        inputTotal = new JTextField(10);
        formPanel.add(inputTotal);

        formPanel.add(new JLabel("Cliente:"));
        inputCliente = new JTextField(10);
        formPanel.add(inputCliente);

        formPanel.add(new JLabel("Data:"));
        inputData = new JTextField(10);
        formPanel.add(inputData);

        add(formPanel); // Adiciona o painel de formulário ao layout principal

        // Painel de Botões
        buttonPanel = new JPanel(new FlowLayout());
        realizaVenda = new JButton("Concluir Venda");
        cancelaVenda = new JButton("Cancelar Venda");
        buttonPanel.add(realizaVenda);
        buttonPanel.add(cancelaVenda);
        add(buttonPanel); // Adiciona o painel de botões ao layout principal

        // Painel da Tabela
        tablePanel = new JPanel(new FlowLayout());
        jSPane = new JScrollPane();
        tableModel = new DefaultTableModel(new Object[][] {}, new String[] { "#", "Imóvel", "Cliente", "Data Início", "Data Fim" });
        table = new JTable(tableModel);
        jSPane.setViewportView(table);
        tablePanel.add(jSPane);
        add(tablePanel); // Adiciona o painel da tabela ao layout principal

        // Inicializar dados da tabela
        new AluguelDAO().criaTabela();
        atualizarTabela();
    }

    // Atualiza a tabela com os dados
    public void atualizarTabela() {
        tableModel.setRowCount(0);
        alugueis = new AluguelDAO().listarTodos();
        for (Aluguel aluguel : alugueis) {
            tableModel.addRow(new Object[] {
                    (alugueis.indexOf(aluguel) + 1),
                    aluguel.getImovel().getCodigo_id(),
                    aluguel.getUsuario().getNome(),
                    aluguel.getData_inicio(),
                    aluguel.getData_fim()
            });
        }
    }
}
