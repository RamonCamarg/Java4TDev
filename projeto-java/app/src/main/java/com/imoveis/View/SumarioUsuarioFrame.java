package com.imoveis.View;

import javax.swing.*;

import com.imoveis.Connection.AluguelDAO;
import com.imoveis.Model.Aluguel;
import com.imoveis.Model.Usuario;

import java.awt.*;
import java.util.List;

public class SumarioUsuarioFrame extends JFrame {
    public SumarioUsuarioFrame(Usuario usuario) {
        setTitle("Sumário do Usuário: " + usuario.getNome());
        setSize(400, 300);
        setLayout(new BorderLayout());

        // Painel para exibir informações
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(0, 1));

        // Exibir as informações do usuário
        infoPanel.add(new JLabel("Nome: " + usuario.getNome()));
        infoPanel.add(new JLabel("CPF: " + usuario.getCpf()));
        infoPanel.add(new JLabel("Telefone: " + usuario.getTelefone()));

        // Informações extras (alugueis, valor total, etc)
        int numAlugueis = buscarNumeroAlugueis(usuario);
        double totalGasto = buscarTotalGasto(usuario);

        infoPanel.add(new JLabel("Número de Aluguéis: " + numAlugueis));
        infoPanel.add(new JLabel("Total Gasto: R$ " + totalGasto));

        add(infoPanel, BorderLayout.CENTER);

        // Botão para fechar o frame
        JButton closeButton = new JButton("Fechar");
        closeButton.addActionListener(e -> dispose());
        add(closeButton, BorderLayout.SOUTH);
    }

    // Função para buscar o número de aluguéis do usuário
    private int buscarNumeroAlugueis(Usuario usuario) {
        List<Aluguel> alugueisByUser = new AluguelDAO().buscarPorUsuario(usuario.getCpf());
        return alugueisByUser.size();
    }

    // Função para buscar o total gasto pelo usuário
    private double buscarTotalGasto(Usuario usuario) {
        List<Aluguel> alugueisByUser = new AluguelDAO().buscarPorUsuario(usuario.getCpf());
        double somaGasto = 0;
        for (Aluguel aluguel : alugueisByUser) {
            somaGasto += aluguel.getImovel().getValor_aluguel();
        }
        return somaGasto;
    }
}
