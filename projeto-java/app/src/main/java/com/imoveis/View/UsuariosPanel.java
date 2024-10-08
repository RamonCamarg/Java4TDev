package com.imoveis.View;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.AbstractCellEditor;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import com.imoveis.Connection.UsuariosDAO;
import com.imoveis.Controller.UsuarioController;
import com.imoveis.Model.Aluguel;
import com.imoveis.Model.Usuario;
import com.imoveis.View.RelatorioUsuarioPanel.ButtonEditor;
import com.imoveis.View.RelatorioUsuarioPanel.ButtonRenderer;

public class UsuariosPanel extends JPanel {
    // Componentes
    private JPanel buttonPanel;
    private JButton cadastraUsuario, editaUsuario, apagaUsuario;
    private JTextField inputCpf, inputNome, inputTelefone, inputEmail;
    private DefaultTableModel tableModel;
    private JTable table;
    private List<Usuario> usuarios;
    private int linhaSelecionada = -1;
    private JScrollPane jSPane;
    private UsuarioController control;

    public UsuariosPanel() {
        super();
        usuarios = new ArrayList<>();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Definindo layout do UsuariosPanel

        // --------------------------*
        // Componentes
        cadastraUsuario = new JButton("Cadastrar");
        apagaUsuario = new JButton("Excluir");
        editaUsuario = new JButton("Editar");
        inputCpf = new JTextField(11); // CPF
        inputNome = new JTextField(20); // Nome Completo
        inputTelefone = new JTextField(15); // Telefone
        inputEmail = new JTextField(20); // Email

        // --------------------------*
        JPanel title = new JPanel(new FlowLayout());
        title.add(new JLabel("Cadastrar Usuarios"));
        add(title);
        // Adicionar os componentes:
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 2, 2)); // InputPanel
        inputPanel.add(new JLabel("Digite o CPF do usuário:"));
        inputPanel.add(inputCpf);
        inputPanel.add(new JLabel("Digite o nome completo do usuário:"));
        inputPanel.add(inputNome);
        inputPanel.add(new JLabel("Digite o telefone do usuário:"));
        inputPanel.add(inputTelefone);
        inputPanel.add(new JLabel("Digite o email do usuário:"));
        inputPanel.add(inputEmail);
        add(inputPanel);

        // --------------------------*
        buttonPanel = new JPanel(); // Painel de botões
        buttonPanel.add(cadastraUsuario);
        buttonPanel.add(editaUsuario);
        buttonPanel.add(apagaUsuario);
        add(buttonPanel); // Adicionando o painel de botões à Tela Principal

        // --------------------------*
        jSPane = new JScrollPane(); // Criando um scrollPane
        add(jSPane);
        tableModel = new DefaultTableModel(new Object[][] {},
                new String[] { "CPF", "Nome Completo", "Telefone", "Email", "Ação" });
        table = new JTable(tableModel);
        jSPane.setViewportView(table);
        // Cria o banco de dados caso não tenha sido criado
        new UsuariosDAO().criaTabela();
        // Atualiza a tabela

        // Define o renderizador e editor de célula para a coluna de ações
        table.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor(new JButton("Ver detalhes")));

        atualizarTabela();


        control = new UsuarioController(usuarios, tableModel, table); // Instanciando o controller

        // Tratamento de eventos:

        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                linhaSelecionada = table.rowAtPoint(e.getPoint());
                if (linhaSelecionada != -1) {
                    inputCpf.setText((String) table.getValueAt(linhaSelecionada, 0));
                    inputNome.setText((String) table.getValueAt(linhaSelecionada, 1));
                    inputTelefone.setText((String) table.getValueAt(linhaSelecionada, 2));
                    inputEmail.setText((String) table.getValueAt(linhaSelecionada, 3));
                }
            }
        });

        // Cadastrar um usuário:
        cadastraUsuario.addActionListener(e -> {
            if (!inputCpf.getText().isEmpty() && !inputNome.getText().isEmpty()
                    && !inputTelefone.getText().isEmpty() && !inputEmail.getText().isEmpty()) {

                control.cadastrarUsuario(inputCpf.getText(), inputNome.getText(), inputTelefone.getText(),
                        inputEmail.getText());
                // Limpa os campos de entrada após a operação de cadastro
                inputCpf.setText("");
                inputNome.setText("");
                inputTelefone.setText("");
                inputEmail.setText("");
            } else {
                JOptionPane.showMessageDialog(inputPanel,
                        "Preencha os campos corretamente para cadastrar um usuário!!", null,
                        JOptionPane.WARNING_MESSAGE);
            }
        });

        // --------------------------*
        // Editar um usuário:
        editaUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int res = JOptionPane.showConfirmDialog(null, "Deseja atualizar as informações deste usuário?",
                        "Editar", JOptionPane.YES_NO_OPTION);
                if (res == JOptionPane.YES_OPTION) {
                    control.atualizar(inputCpf.getText(), inputNome.getText(), inputTelefone.getText(),
                            inputEmail.getText());
                }
            }
        });

        // --------------------------*
        // Apagar um usuário:
        apagaUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int res = JOptionPane.showConfirmDialog(null, "Deseja excluir este usuário?",
                        "Excluir", JOptionPane.YES_NO_OPTION);
                if (res == JOptionPane.YES_OPTION) {
                    control.apagar(inputCpf.getText());
                    // Limpa os campos de entrada após a operação de exclusão
                    inputCpf.setText("");
                    inputNome.setText("");
                    inputTelefone.setText("");
                    inputEmail.setText("");
                }
            }
        });
    }

    public void atualizarTabela() {
        tableModel.setRowCount(0); // Limpa todas as linhas existentes na tabela
        usuarios = new UsuariosDAO().listarTodos();
        // Obtém os usuários atualizados do banco de dados
        for (Usuario usuario : usuarios) {
            System.out.println(usuario.getCpf());
            // Adiciona os dados de cada usuário como uma nova linha na tabela Swing
            tableModel.addRow(
                    new Object[] { usuario.getCpf(), usuario.getNome(), usuario.getTelefone(), usuario.getEmail() });
        }
    }

    // Renderizador personalizado para o botão
    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setText("Ver detalhes");
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
            Usuario usuario = usuarios.get(row); // Obtém o aluguel correspondente à linha
            abrirFrameDeSumario(usuario);
            fireEditingStopped(); // Para de editar a célula
        }
    }

    // Método para abrir o frame de sumário
    private void abrirFrameDeSumario(Usuario usuario) {
        new SumarioUsuarioFrame(usuario).setVisible(true);
    }
}
