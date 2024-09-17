import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Classe Item
class Item {
    private String nome;
    private int quantidade;

    public Item(String nome, int quantidade) {
        this.nome = nome;
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "Item: " + nome + ", Quantidade: " + quantidade;
    }
}

// Classe Sistema de Estoque
public class Exercicio6 {
    private static final String ARQUIVO_ESTOQUE = "estoque.txt";
    private List<Item> estoque;

    public Exercicio6() {
        this.estoque = new ArrayList<>();
        carregarEstoque();
    }

    // Método para adicionar um item ao estoque
    public void adicionarItem(String nome, int quantidade) {
        for (Item item : estoque) {
            if (item.getNome().equalsIgnoreCase(nome)) {
                item.setQuantidade(item.getQuantidade() + quantidade);
                System.out.println("Quantidade atualizada com sucesso.");
                return;
            }
        }
        estoque.add(new Item(nome, quantidade));
        System.out.println("Item adicionado com sucesso.");
    }

    // Método para remover um item do estoque
    public void removerItem(String nome) {
        for (Item item : estoque) {
            if (item.getNome().equalsIgnoreCase(nome)) {
                estoque.remove(item);
                System.out.println("Item removido com sucesso.");
                return;
            }
        }
        System.out.println("Erro: Item não encontrado no estoque.");
    }

    // Método para listar todos os itens do estoque
    public void listarItens() {
        if (estoque.isEmpty()) {
            System.out.println("Estoque vazio.");
        } else {
            System.out.println("Lista de itens no estoque:");
            for (Item item : estoque) {
                System.out.println(item);
            }
        }
    }

    // Método para salvar o estoque em um arquivo
    public void salvarEstoque() {
        try (FileWriter writer = new FileWriter(ARQUIVO_ESTOQUE);
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
            for (Item item : estoque) {
                bufferedWriter.write(item.getNome() + ";" + item.getQuantidade());
                bufferedWriter.newLine();
            }
            System.out.println("Estoque salvo com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar o estoque: " + e.getMessage());
        }
    }

    // Método para carregar o estoque de um arquivo
    public void carregarEstoque() {
        File arquivo = new File(ARQUIVO_ESTOQUE);
        if (!arquivo.exists()) {
            System.out.println("Arquivo de estoque não encontrado. Criando um novo.");
            return;
        }

        try (FileReader reader = new FileReader(ARQUIVO_ESTOQUE);
             BufferedReader bufferedReader = new BufferedReader(reader)) {
            String linha;
            while ((linha = bufferedReader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length == 2) {
                    String nome = dados[0];
                    int quantidade = Integer.parseInt(dados[1]);
                    estoque.add(new Item(nome, quantidade));
                }
            }
            System.out.println("Estoque carregado com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao carregar o estoque: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Exercicio6 sistema = new Exercicio6();
        boolean continuar = true;

        while (continuar) {
            System.out.println("\nMenu:");
            System.out.println("1. Adicionar item");
            System.out.println("2. Remover item");
            System.out.println("3. Listar itens");
            System.out.println("4. Sair e salvar estoque");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome do item: ");
                    String nome = scanner.nextLine();
                    System.out.print("Digite a quantidade do item: ");
                    int quantidade = scanner.nextInt();
                    sistema.adicionarItem(nome, quantidade);
                    break;
                case 2:
                    System.out.print("Digite o nome do item a ser removido: ");
                    String nomeRemover = scanner.nextLine();
                    sistema.removerItem(nomeRemover);
                    break;
                case 3:
                    sistema.listarItens();
                    break;
                case 4:
                    sistema.salvarEstoque();
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        scanner.close();
    }
}
