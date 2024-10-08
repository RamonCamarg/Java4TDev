package com.imoveis.Controller;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.imoveis.Connection.UsuariosDAO;
import com.imoveis.Model.Usuario;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class UsuarioController {
    // CRUD
    private List<Usuario> usuarios;
    private DefaultTableModel tableModel;
    private JTable table;

    // Atualiza a tabela com os usuários do banco de dados
    public void atualizarTabela() {
        tableModel.setRowCount(0); // Limpa todas as linhas existentes na tabela
        usuarios = new UsuariosDAO().listarTodos();
        // Obtém os usuários atualizados do banco de dados
        for (Usuario usuario : usuarios) {
            // Adiciona os dados de cada usuário como uma nova linha na tabela Swing
            tableModel.addRow(
                    new Object[] { usuario.getCpf(), usuario.getNome(), usuario.getTelefone(), usuario.getEmail() });
        }
    }

    // Método para cadastrar um novo usuário
    public void cadastrarUsuario(String cpf, String nomeCompleto, String telefone, String email) {
        try {
            if (validaCpf(cpf)) { // Valida CPF antes de cadastrar
                Usuario usuario = new Usuario(cpf.trim().toUpperCase(), nomeCompleto.trim().toUpperCase(),
                        telefone.trim(), email.trim());
                usuarios.add(usuario);
                // Adiciona ao banco de dados
                new UsuariosDAO().cadastrar(cpf, nomeCompleto, telefone, email);
                atualizarTabela(); // Atualiza a tabela
                JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Verifique se o CPF está correto!", "ERRO!",
                        JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception err) {
            System.out.println(err.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar usuário!", "ERRO!", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para apagar um usuário do banco de dados
    public void apagar(String cpf) {
        new UsuariosDAO().apagar(cpf); // Chama o método de exclusão no banco de dados
        atualizarTabela(); // Atualiza a tabela de exibição após a exclusão
        JOptionPane.showMessageDialog(table, "Usuário removido!", null, JOptionPane.INFORMATION_MESSAGE);
    }

    // Método para atualizar os dados de um usuário
    public void atualizar(String cpf, String nomeCompleto, String telefone, String email) {
        try {
            if (validaCpf(cpf)) { // Valida CPF antes de atualizar
                new UsuariosDAO().atualizar(cpf, nomeCompleto, telefone, email); // Chama o método de atualização no
                                                                                 // banco de dados
                JOptionPane.showMessageDialog(null, "Usuário atualizado com sucesso!", null,
                        JOptionPane.INFORMATION_MESSAGE);
                atualizarTabela(); // Atualiza a tabela de exibição após a atualização
            } else {
                JOptionPane.showMessageDialog(null, "Verifique se o CPF está correto!", "ERRO!",
                        JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception err) {
            System.out.println(err.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao atualizar usuário!", "ERRO!", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para validar CPF
    public boolean validaCpf(String cpf) {
        return cpf.matches("[0-9]+") && cpf.length() == 11; // Verifica se o CPF tem apenas dígitos e 11 caracteres
    }
}
