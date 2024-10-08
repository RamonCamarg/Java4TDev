package com.imoveis.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import com.imoveis.GerarRelatorioPDF;
import com.imoveis.Connection.AluguelDAO;
import com.imoveis.Model.Aluguel;

public class RelatorioUsuarioPanel extends JPanel {
    private List<Aluguel> alugueis;
    private JTextField inputCpf;
    private JButton btnBuscar;
    private JPanel titlePanel, mainPanel;
    private JTable tableAlugueis;
    private DefaultTableModel tableModel;

    public RelatorioUsuarioPanel() {
        super();
        setLayout(new BorderLayout());
        titlePanel = new JPanel(new FlowLayout());
        JLabel title = new JLabel("Gerar relatórios para usuário");
        titlePanel.add(title);
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout()); // Usando GridBagLayout para maior controle
        GridBagConstraints gbc = new GridBagConstraints();

        // Label para CPF
        JLabel labelCpf = new JLabel("Digite o CPF do cliente:");
        gbc.gridx = 0; // Posição coluna 0
        gbc.gridy = 0; // Posição linha 0
        gbc.insets = new Insets(5, 5, 5, 5); // Espaçamento
        gbc.anchor = GridBagConstraints.LINE_END; // Alinhamento à direita
        mainPanel.add(labelCpf, gbc);

        // Input para CPF
        inputCpf = new JTextField(15);
        gbc.gridx = 1; // Posição coluna 1
        gbc.gridy = 0; // Mesma linha que a label
        gbc.anchor = GridBagConstraints.LINE_START; // Alinhamento à esquerda
        mainPanel.add(inputCpf, gbc);

        // Botão de buscar
        btnBuscar = new JButton("Buscar");


        gbc.gridx = 0; // Posição coluna 0
        gbc.gridy = 1; // Próxima linha
        gbc.gridwidth = 2; // O botão ocupará duas colunas
        gbc.anchor = GridBagConstraints.CENTER; // Centralizar o botão
        mainPanel.add(btnBuscar, gbc);

        // Tabela para exibir os aluguéis
        String[] colunas = { "Imóvel", "Descrição", "Data Início", "Data Fim", "Valor Aluguel", "Ação" };
        tableModel = new DefaultTableModel(colunas, 0);
        tableAlugueis = new JTable(tableModel);

        // Define o renderizador e editor de célula para a coluna de ações
        tableAlugueis.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer());
        tableAlugueis.getColumnModel().getColumn(5).setCellEditor(new ButtonEditor(new JButton("Gerar Relatório")));

        JScrollPane scrollPane = new JScrollPane(tableAlugueis);

        // Adicionar tabela ao painel
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2; // Tabela ocupa as duas colunas
        gbc.fill = GridBagConstraints.BOTH; // Preenche o espaço disponível
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        mainPanel.add(scrollPane, gbc);

        add(titlePanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);

        // Ação ao clicar no botão "Buscar"
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarAlugueisPorCpf(inputCpf.getText());
            }
        });
    }

    private void buscarAlugueisPorCpf(String cpf) {
        // Limpa os resultados anteriores
        tableModel.setRowCount(0);

        // Busca os aluguéis pelo CPF do cliente
        alugueis = new AluguelDAO().buscarPorUsuario(cpf);
        if (alugueis.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Usuário não encontrado, verifique o CPF e digite novamente", "ERRO",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Usuário encontrado!", "Sucesso!",
                    JOptionPane.INFORMATION_MESSAGE);
            // Preenche a tabela com os resultados
            for (Aluguel aluguel : alugueis) {
                Object[] rowData = {
                        aluguel.getImovel().getCodigo_id(),
                        aluguel.getImovel().getDescricao(),
                        aluguel.getData_inicio(),
                        aluguel.getData_fim(),
                        aluguel.getImovel().getValor_aluguel(),
                        "Gerar Relatório" // Texto do botão
                };
                tableModel.addRow(rowData);
            }
        }
    }

    private void gerarRelatorio(Aluguel aluguel) { // Gera relatório PDF para o usuário
        GerarRelatorioPDF gerarPdf = new GerarRelatorioPDF();
        gerarPdf.gerarRelatorioPDF(aluguel);
    }

    // Renderizador personalizado para o botão
    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setText("Gerar Relatório");
        }

        @Override
        public java.awt.Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {
            return this;
        }
    }

    // Editor personalizado para o botão
    class ButtonEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {
        private JButton button;
        private int row;

        public ButtonEditor(JButton button) {
            this.button = button;
            this.button.addActionListener(this);
        }

        @Override
        public Object getCellEditorValue() {
            return button.getText();
        }

        @Override
        public java.awt.Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
                int column) {
            this.row = row;
            return button;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Aluguel aluguel = alugueis.get(row); // Obtém o aluguel correspondente à linha
            gerarRelatorio(aluguel); // Gera o relatório
            fireEditingStopped(); // Para de editar a célula
        }
    }
}
