package com.example;

import java.util.Scanner;

import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        AgendaTelefonica agenda = new AgendaTelefonica(5);
        int operador = 0;
        try {
            do {
                System.out.println("\n---Agenda Telefonica---");
                System.out.println("1-Adicionar Contato");
                System.out.println("2-Listar Contato");
                System.out.println("3-Buscar Contato(nome)");
                System.out.println("4-Remover Contato(nome)");
                System.out.println("5-Sair...");
                try {
                    operador = Integer.parseInt(
                            JOptionPane.showInputDialog(
                                    "Digite a Operação"));
                } catch (NumberFormatException e) {
                    System.err.println(e);
                    operador = 0;
                }

                switch (operador) {
                    case 1:
                        try {
                            String nome = JOptionPane.showInputDialog("Digite o Nome");
                            String telefone = JOptionPane.showInputDialog("Digite o Telefone");
                            Contato contato = new Contato(nome, telefone);
                            agenda.addContato(contato);
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                        break;
                    case 2:
                        agenda.listarContatos();
                        break;
                    case 3:
                        try {
                            String nome = JOptionPane.showInputDialog("Informe um nome");
                            System.out.println(agenda.buscarContato(nome).toString());
                        } catch (Exception e) {
                            System.err.println(e);
                        }
                        break;
                    case 4:
                        try {
                            String nomeRemove = JOptionPane.showInputDialog(
                                "Informe um nome para Remover");
                            agenda.removerConta(nomeRemove);
                        } catch (Exception e) {
                           System.err.println(e);
                        }
                        break;
                    default:
                        System.out.println("Digite um nº Válido");
                        break;
                }
            } while (operador != 5);
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}