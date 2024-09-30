package com.example;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class JanelaCalculadora extends JFrame {
    public JanelaCalculadora() {
        super("Calculadora Avançada");
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        CalculadoraSimples simples = new CalculadoraSimples();
        JTabbedPane cientifica = new JTabbedPane();
        simples.addTab(getTitle(), simples);
        cientifica.addTab(getTitle(), cientifica);
        this.add(simples);
        this.add(cientifica);

        this.setVisible(true);

    }
}
