package main.java.com.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    // Exibir todos os clientes
    public List<Cliente> listarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                clientes.add(new Cliente(id, nome, email));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientes;
    }

    // Adicionar um novo cliente
    public void adicionarCliente(String nome, String email) {
        String sql = "INSERT INTO clientes (nome, email) VALUES (?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nome);
            statement.setString(2, email);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Atualizar o e-mail de um cliente baseado no ID
    public void atualizarEmail(int id, String novoEmail) {
        String sql = "UPDATE clientes SET email = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, novoEmail);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Excluir um cliente baseado no ID
    public void excluirCliente(int id) {
        String sql = "DELETE FROM clientes WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
