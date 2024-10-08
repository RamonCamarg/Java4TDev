package com.imoveis.Controller;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.imoveis.Connection.ImovelDAO;
import com.imoveis.Model.Imovel;

public class ImovelController {
    private List<Imovel> imoveis;
    private DefaultTableModel tableModel;
    private JTable table;

    public ImovelController(List<Imovel> imoveis, DefaultTableModel tableModel, JTable table) {
        this.imoveis = imoveis;
        this.tableModel = tableModel;
        this.table = table;
    }

    public void atualizarTabela() {
        tableModel.setRowCount(0);
        imoveis = new ImovelDAO().listarTodos();
        for (Imovel imovel : imoveis) {
            tableModel.addRow(
                    new Object[] { imovel.getCodigo_id(), imovel.getCidade(), imovel.getEstado(), imovel.getEndereco(),
                            imovel.getValor_aluguel(), imovel.getDescricao(), imovel.getStatus() });
        }
    }

    // Método para gerar um código aleatório de 5 dígitos
    private int gerarCodigoId() {
        return (int) (Math.random() * 90000) + 10000; // Gera um número entre 10000 e 99999
    }

    public void cadastrarImovel(String cidade, String estado, String endereco, double valor_aluguel, String descricao,
            String status) {
        if (cidade.isEmpty() || estado.isEmpty() || endereco.isEmpty() || String.valueOf(valor_aluguel).isEmpty()
                || valor_aluguel <= 0 || descricao.isEmpty() || status.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos corretamente!", "ERRO", JOptionPane.ERROR_MESSAGE);
        }
        try {
            Imovel imovel = new Imovel();
            imovel.setCodigo_id(String.valueOf(gerarCodigoId()));
            imovel.setCidade(cidade);
            imovel.setEstado(estado);
            imovel.setEndereco(endereco);
            imovel.setValor_aluguel(valor_aluguel);
            imovel.setDescricao(descricao);
            imovel.setStatus(status);
            new ImovelDAO().cadastrar(imovel);
            atualizarTabela();
            JOptionPane.showMessageDialog(null, "Imóvel cadastrado com sucesso!");
        } catch (Exception err) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar imóvel: " + err.getMessage(), "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void atualizarImovel(Imovel imovel, String id) {
        try {
            new ImovelDAO().atualizar(imovel, id);
            atualizarTabela();
            JOptionPane.showMessageDialog(null, "Imóvel atualizado com sucesso!");
        } catch (Exception err) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar imóvel: " + err.getMessage(), "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void apagarImovel(String id) {
        try {
            new ImovelDAO().apagar(id);
            atualizarTabela();
            JOptionPane.showMessageDialog(null, "Imóvel excluído com sucesso!");
        } catch (Exception err) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir imóvel: " + err.getMessage(), "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
