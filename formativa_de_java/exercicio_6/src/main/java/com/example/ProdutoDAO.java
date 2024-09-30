package main.java.com.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProdutoDAO {
    public List<Produto> listarProdutos() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produtos";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                double preco = resultSet.getDouble("preco");
                produtos.add(new Produto(id, nome, preco));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produtos;
    }

    public Produto produtoMaisCaro(List<Produto> produtos) {
        return produtos.stream().max(Comparator.comparing(Produto::getPreco)).orElse(null);
    }

    public Produto produtoMaisBarato(List<Produto> produtos) {
        return produtos.stream().min(Comparator.comparing(Produto::getPreco)).orElse(null);
    }

    public double calcularMediaPrecos(List<Produto> produtos) {
        return produtos.stream().mapToDouble(Produto::getPreco).average().orElse(0.0);
    }
}
