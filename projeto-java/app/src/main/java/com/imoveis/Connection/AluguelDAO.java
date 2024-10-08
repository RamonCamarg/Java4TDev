package com.imoveis.Connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.imoveis.Model.Aluguel;
import com.imoveis.Model.Imovel;
import com.imoveis.Model.Usuario;

public class AluguelDAO {

    private Connection connection;
    private List<Aluguel> alugueis;

    public AluguelDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    public void criaTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS alugueis ("
                + "id SERIAL PRIMARY KEY, "
                + "data_inicio DATE NOT NULL, "
                + "data_fim DATE NOT NULL, "
                + "usuario_id VARCHAR(255) REFERENCES usuarios_imobiliaria(cpf), "
                + "imovel_id VARCHAR(255) REFERENCES imoveis(codigo_id)"
                + ")";

        try (Statement stmt = this.connection.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela de alugueis criada com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar a tabela de alugueis: " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(this.connection);
        }
    }

    // Método para cadastrar um aluguel no banco de dados
    public void cadastrar(Aluguel aluguel) {
        String sql = "INSERT INTO alugueis (data_inicio, data_fim, usuario_id, imovel_id) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setDate(1, new java.sql.Date(aluguel.getData_inicio().getTime()));
            stmt.setDate(2, new java.sql.Date(aluguel.getData_fim().getTime()));
            stmt.setString(3, aluguel.getUsuario().getCpf()); // Supondo que o ID do usuário é um inteiro
            stmt.setString(4, aluguel.getImovel().getCodigo_id()); // Supondo que o código do imóvel é um inteiro
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao cadastrar aluguel: " + e.getMessage());
        }
    }

    // Método para listar todos os alugueis
    public List<Aluguel> listarTodos() {
        alugueis = new ArrayList<>();
        String sql = "SELECT * FROM alugueis";

        try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Aluguel aluguel = new Aluguel();
                aluguel.setData_inicio(rs.getDate("data_inicio"));
                aluguel.setData_fim(rs.getDate("data_fim"));

                // Preencher usuário
                Usuario usuario = new UsuariosDAO().buscarPorId(rs.getString("usuario_id"));
                aluguel.setUsuario(usuario);

                // Preencher imóvel
                Imovel imovel = new ImovelDAO().buscarPorCodigoId(rs.getString("imovel_id"));
                aluguel.setImovel(imovel);

                alugueis.add(aluguel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao listar alugueis: " + e.getMessage());
        }

        return alugueis;
    }

    // Método para buscar aluguel por ID do usuário
    public List<Aluguel> buscarPorUsuario(String usuarioId) {
        List<Aluguel> alugueis = new ArrayList<>();
        String sql = "SELECT * FROM alugueis WHERE usuario_id = ?";

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuarioId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Aluguel aluguel = new Aluguel();
                    aluguel.setData_inicio(rs.getDate("data_inicio"));
                    aluguel.setData_fim(rs.getDate("data_fim"));

                    // Preencher usuário
                    Usuario usuario = new UsuariosDAO().buscarPorId(rs.getString("usuario_id"));
                    aluguel.setUsuario(usuario);

                    // Preencher imóvel
                    Imovel imovel = new ImovelDAO().buscarPorCodigoId(rs.getString("imovel_id"));
                    aluguel.setImovel(imovel);

                    alugueis.add(aluguel);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar alugueis do usuário: " + e.getMessage());
        }

        return alugueis;
    }

    // Método para atualizar um aluguel
    public void atualizar(Aluguel aluguel, int id) {
        String sql = "UPDATE alugueis SET data_inicio = ?, data_fim = ?, usuario_id = ?, imovel_id = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, new java.sql.Date(aluguel.getData_inicio().getTime()));
            stmt.setDate(2, new java.sql.Date(aluguel.getData_fim().getTime()));
            stmt.setString(3, aluguel.getUsuario().getCpf());
            stmt.setString(4, aluguel.getImovel().getCodigo_id());
            stmt.setInt(5, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao atualizar aluguel: " + e.getMessage());
        }
    }

    // Método para apagar um aluguel
    public void apagar(int id) {
        String sql = "DELETE FROM alugueis WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao apagar aluguel: " + e.getMessage());
        }
    }
}
