package com.imoveis.Connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.imoveis.Model.Usuario;

public class UsuariosDAO {
    // Atributos
    private Connection connection;
    private List<Usuario> usuarios;

    // Construtor
    public UsuariosDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    // Métodos
    // Criar Tabela
    public void criaTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS usuarios_imobiliaria (CPF VARCHAR(20) PRIMARY KEY, NOME VARCHAR(255), TELEFONE VARCHAR(15), EMAIL VARCHAR(255))";
        try (Statement stmt = this.connection.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela criada com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar a tabela: " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(this.connection);
        }
    }

    // Listar todos os usuários cadastrados
    public List<Usuario> listarTodos() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        usuarios = new ArrayList<>();

        try {
            stmt = connection.prepareStatement("SELECT * FROM usuarios_imobiliaria");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("email"),
                        rs.getString("telefone"));
                usuarios.add(usuario);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt, rs);
        }
        return usuarios;
    }

    // Apagar tabela
    public void apagarTabela() {
        String sql = "DROP TABLE usuarios_imobiliaria";
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Tabela apagada com sucesso.");
        } catch (Exception e) {
            throw new RuntimeException("Erro ao apagar tabela.", e);
        } finally {
            ConnectionFactory.closeConnection(this.connection);
        }
    }

    // Cadastrar um novo usuário
    public void cadastrar(String cpf, String nome, String telefone, String email) {
        PreparedStatement stmt = null;
        String sql = "INSERT INTO usuarios_imobiliaria(cpf, nome, telefone, email) VALUES (?, ?, ?, ?)";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, cpf.toUpperCase().trim());
            stmt.setString(2, nome.toUpperCase().trim());
            stmt.setString(3, telefone.trim());
            stmt.setString(4, email.toLowerCase().trim());
            stmt.executeUpdate();
            System.out.println("Dados inseridos com sucesso");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir dados no banco de dados.", e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }

    // Atualizar dados de um usuário
    public void atualizar(String cpf, String nome, String telefone, String email) {
        PreparedStatement stmt = null;
        String sql = "UPDATE usuarios_imobiliaria SET nome = ?, telefone = ?, email = ? WHERE cpf = ?";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome.toUpperCase().trim());
            stmt.setString(2, telefone.trim());
            stmt.setString(3, email.toLowerCase().trim());
            stmt.setString(4, cpf);
            stmt.executeUpdate();
            System.out.println("Dados atualizados com sucesso");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar dados no banco de dados.", e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }

    // Apagar um usuário
    public void apagar(String cpf) {
        PreparedStatement stmt = null;
        String sql = "DELETE FROM usuarios_imobiliaria WHERE cpf = ?";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, cpf);
            stmt.executeUpdate();
            System.out.println("Dado apagado com sucesso");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao apagar dados no banco de dados.", e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }

    public Usuario buscarPorId(String cpf) {
        String sql = "SELECT * FROM usuarios_imobiliaria WHERE cpf = ?";
        Usuario usuario = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setCpf(cpf);
                    usuario.setNome(rs.getString("nome"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setTelefone(rs.getString("telefone"));
                    // Adicione mais campos conforme necessário
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar usuário por ID: " + e.getMessage());
        }

        return usuario;
    }
}
