package com.imoveis.Connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.imoveis.Model.Imovel;

public class ImovelDAO {
    private Connection connection;

    public ImovelDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    public void criaTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS imoveis (id SERIAL PRIMARY KEY, codigo_id VARCHAR(255) UNIQUE, cidade VARCHAR(255), estado VARCHAR(255), endereco VARCHAR(255), valor_aluguel DECIMAL(10, 2), descricao VARCHAR(255), status VARCHAR(50))";
        try (Statement stmt = this.connection.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela de imóveis criada com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar a tabela de imóveis: " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(this.connection);
        }
    }

    public List<Imovel> listarTodos() {
        List<Imovel> imoveis = new ArrayList<>();
        String sql = "SELECT * FROM imoveis";
        try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Imovel imovel = new Imovel();
                imovel.setCodigo_id(rs.getString("codigo_id"));
                imovel.setCidade(rs.getString("cidade"));
                imovel.setEstado(rs.getString("estado"));
                imovel.setEndereco(rs.getString("endereco"));
                imovel.setValor_aluguel(rs.getDouble("valor_aluguel"));
                imovel.setDescricao(rs.getString("descricao"));
                imovel.setStatus(rs.getString("status"));
                imoveis.add(imovel);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(this.connection);
        }
        return imoveis;
    }

    public void cadastrar(Imovel imovel) {
        String sql = "INSERT INTO imoveis(codigo_id,cidade, estado, endereco, valor_aluguel, descricao, status) VALUES (?, ?, ?, ?, ?, ?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, imovel.getCodigo_id());
            stmt.setString(2, imovel.getCidade());
            stmt.setString(3, imovel.getEstado());
            stmt.setString(4, imovel.getEndereco());
            stmt.setDouble(5, imovel.getValor_aluguel());
            stmt.setString(6, imovel.getDescricao());
            stmt.setString(7, imovel.getStatus());
            stmt.executeUpdate();
            System.out.println("Imóvel cadastrado com sucesso");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar imóvel no banco de dados."+e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(this.connection);
        }
    }

    public void atualizar(Imovel imovel, String id) {
        String sql = "UPDATE imoveis SET cidade = ?, estado = ?, endereco = ?, valor_aluguel = ?, descricao = ?, status = ? WHERE codigo_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, imovel.getCidade());
            stmt.setString(2, imovel.getEstado());
            stmt.setString(3, imovel.getEndereco());
            stmt.setDouble(4, imovel.getValor_aluguel());
            stmt.setString(5, imovel.getDescricao());
            stmt.setString(6, imovel.getStatus());
            stmt.setString(7, id);
            stmt.executeUpdate();
            System.out.println("Imóvel atualizado com sucesso");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar imóvel no banco de dados.", e);
        } finally {
            ConnectionFactory.closeConnection(this.connection);
        }
    }

    public void apagar(String codigo_id) {
        String sql = "DELETE FROM imoveis WHERE codigo_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, codigo_id);
            stmt.executeUpdate();
            System.out.println("Imóvel excluído com sucesso");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir imóvel do banco de dados.", e);
        } finally {
            ConnectionFactory.closeConnection(this.connection);
        }
    }

    public Imovel buscarPorCodigoId(String codigoId) {
        String sql = "SELECT * FROM imoveis WHERE codigo_id = ?";
        Imovel imovel = null;

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, codigoId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    imovel = new Imovel();
                    imovel.setCodigo_id(rs.getString("codigo_id"));
                    imovel.setCidade(rs.getString("cidade"));
                    imovel.setEstado(rs.getString("estado"));
                    imovel.setEndereco(rs.getString("endereco"));
                    imovel.setValor_aluguel(rs.getDouble("valor_aluguel"));
                    imovel.setDescricao(rs.getString("descricao"));
                    imovel.setStatus(rs.getString("status"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar imóvel por código: " + e.getMessage());
        }

        return imovel;
    }
}
