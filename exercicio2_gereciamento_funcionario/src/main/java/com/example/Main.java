package com.example;

import javax.print.attribute.standard.JobHoldUntil;
import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        FuncionarioController gerencia = new FuncionarioController();
        int operacao = 0;
        do {
            try {
                operacao = Integer.parseInt(JOptionPane.showInputDialog("\n"
                        + "---Gerenciamento de Funcionário \n"
                        + "1 - Cadastrar Funcionário \n"
                        + "2 - Listar Funcinários \n"
                        + "3 - Remover Funcionário \n"
                        + "4 - Calcular Média Salarial \n"
                        + "5 - Sair \n"));
            } catch (Exception e) {
                System.err.println(e);
                operacao = 0;
            }
            switch (operacao) {
                case 1:
                    String nome = JOptionPane.showInputDialog(
                            "Digite o nome");
                    int idade = Integer.parseInt(JOptionPane.showInputDialog(
                            "Digite a Idade"));
                    double salario = Double.parseDouble(JOptionPane.showInputDialog(
                            "Digite o Salario"));
                    gerencia.addFuncionario(new Funcionario(nome, idade, salario));
                    break;
                case 2:
                    gerencia.listarFuncionarios();
                    break;
                case 3:
                    String nomef = JOptionPane.showInputDialog(
                            "Informe o nome a ser Removido");
                    gerencia.removerFuncionario(nomef);
                    break;
                case 4:
                    gerencia.mediaSal();
                case 5:
                    System.out.println("Saindo...");
                default:
                    System.out.println("Digite uma Opção Válida");
                    break;
            }

        } while (operacao != 5);

    }
}