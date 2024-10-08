package com.imoveis;

import javax.swing.SwingUtilities;

import com.imoveis.View.MainFrame;

public class Main {
    public static void main(String[] args) {
       SwingUtilities.invokeLater(new Runnable() {

        @Override
        public void run() {
            new MainFrame();
        }
        
       });
    }
}