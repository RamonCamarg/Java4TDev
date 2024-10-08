package com.imoveis.Controller;

import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.imoveis.Connection.AluguelDAO;
import com.imoveis.Connection.ImovelDAO;
import com.imoveis.Connection.UsuariosDAO;
import com.imoveis.Model.Aluguel;
import com.imoveis.Model.Imovel;
import com.imoveis.Model.Usuario;

public class AluguelController {
private List<Aluguel> alugueis;
    private DefaultTableModel tableModel;
    private JTable table;

    public AluguelController(List<Aluguel> alugueis, DefaultTableModel tableModel, JTable table) {
        this.alugueis = alugueis;
        this.tableModel = tableModel;
        this.table = table;
    }

    public void atualizarTabela() {
        tableModel.setRowCount(0);
        alugueis = new AluguelDAO().listarTodos();
        for (Aluguel aluguel : alugueis) {
            tableModel.addRow(new Object[] { 
                aluguel.getImovel().getCodigo_id(),
                aluguel.getImovel().getCidade(),
                aluguel.getUsuario().getNome(),
                aluguel.getData_inicio(),
                aluguel.getData_fim()
            });
        }
    }

    public void realizarAluguel(String imovelCodigoId, String usuarioCpf, Date dataInicio, Date dataFim) {
        try {
            Imovel imovel = new ImovelDAO().buscarPorCodigoId(imovelCodigoId);
            Usuario usuario = new UsuariosDAO().buscarPorId(usuarioCpf);
            
            if (imovel == null || usuario == null) {
                JOptionPane.showMessageDialog(null, "Imóvel ou usuário não encontrados!", "ERRO", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Aluguel aluguel = new Aluguel(dataInicio, dataFim, usuario, imovel);
            new AluguelDAO().cadastrar(aluguel);
            atualizarTabela();
            JOptionPane.showMessageDialog(null, "Aluguel realizado com sucesso!");
        } catch (Exception err) {
            System.out.println(err.getMessage());
            JOptionPane.showMessageDialog(null,
                    "Verifique se os dados escritos estão corretos e tente novamente!", "ERRO!",
                    JOptionPane.WARNING_MESSAGE);
        }
    }
}
