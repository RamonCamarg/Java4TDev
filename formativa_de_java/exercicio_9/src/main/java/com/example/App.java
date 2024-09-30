package com.example;

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ClienteDAO clienteDAO = new ClienteDAO();

        while (true) {
            System.out.println("1. Listar clientes");
            System.out.println("2. Adicionar cliente");
            System.out.println("3. Atualizar e-mail de cliente");
            System.out.println("4. Excluir cliente");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    List<Cliente> clientes = clienteDAO.listarClientes();
                    System.out.println("Lista de clientes:");
                    for (Cliente cliente : clientes) {
                        System.out.printf("ID: %d, Nome: %s, E-mail: %s%n", cliente.getId(), cliente.getNome(), cliente.getEmail());
                    }
                    break;
                case 2:
                    System.out.print("Nome do cliente: ");
                    String nome = scanner.nextLine();
                    System.out.print("E-mail do cliente: ");
                    String email = scanner.nextLine();
                    clienteDAO.adicionarCliente(nome, email);
                    System.out.println("Cliente adicionado com sucesso!");
                    break;
                case 3:
                    System.out.print("ID do cliente a ser atualizado: ");
                    int idParaAtualizar = scanner.nextInt();
                    scanner.nextLine(); // Consumir a nova linha
                    System.out.print("Novo e-mail: ");
                    String novoEmail = scanner.nextLine();
                    clienteDAO.atualizarEmail(idParaAtualizar, novoEmail);
                    System.out.println("E-mail atualizado com sucesso!");
                    break;
                case 4:
                    System.out.print("ID do cliente a ser excluído: ");
                    int idParaExcluir = scanner.nextInt();
                    clienteDAO.excluirCliente(idParaExcluir);
                    System.out.println("Cliente excluído com sucesso!");
                    break;
                case 5:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
