package com.imoveis.View;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class MainFrame extends JFrame {

    public MainFrame() {
        super("Sistema de Alugueis de Imoveis");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        JPanel mainPanel = new JPanel();
        add(mainPanel);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // JPanel logo = new JPanel();
        // JLabel img = new JLabel();
        // ImageIcon iconMenu = new
        // ImageIcon(getClass().getResource("../../assets/logo2.png"));
        // img.setIcon(iconMenu);
        // logo.add(img, FlowLayout.LEFT); // Adicionando uma imagem ao menu do app
        // mainPanel.add(logo); // Adicionando o painel logo ao painel Principal

        // ---------------------*
        // Aplicativo principal:
        JTabbedPane abas = new JTabbedPane();
        abas.add("Imóveis", new ImovelPanel()); // Adiciona o painel de imoveis ao TabbedPane
        abas.add("Clientes", new UsuariosPanel()); // Adiciona o painel de clientes ao TabbedPane
        abas.add("Registro de aluguéis", new AluguelPanel()); // Adiciona o painel de alugueis ao TabbedPane
        abas.add("Gerar relatórios", new RelatorioUsuarioPanel()); // Adiciona o painel de alugueis ao TabbedPane
        mainPanel.add(abas);
     
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                int res = JOptionPane.showConfirmDialog(null,
                        "Deseja realmente sair?",
                        "Gerenciamento", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (res == JOptionPane.YES_OPTION) {
                    setDefaultCloseOperation(2);
                }
            } // Questiona o usuário se realmente ele deseja fechar a aplicação

        });

        // sair.addActionListener(e -> {
        // // new Menu().run();
        // this.dispose();
        // });
        pack();
        setVisible(true);
    }
}
