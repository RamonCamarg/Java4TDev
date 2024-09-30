import java.io.*;  // Importa todas as classes de entrada/saída
import java.util.ArrayList;  // Importa a classe ArrayList para armazenar os itens
import java.util.Scanner;  // Importa a classe Scanner para capturar entrada do usuário

// Classe Item que representa um item no estoque
class Item {
    String nome;  // Nome do item
    int quantidade;  // Quantidade do item

    // Construtor da classe Item
    Item(String nome, int quantidade) {
        this.nome = nome;  // Inicializa o nome do item
        this.quantidade = quantidade;  // Inicializa a quantidade do item
    }

    // Sobrescreve o método toString para exibir o item de forma legível
    @Override
    public String toString() {
        return nome + ": " + quantidade;  // Exibe o item no formato "nome: quantidade"
    }
}

// Classe SistemaEstoque que gerencia o estoque
public class exercicio_6_1 {
    private static final String ARQUIVO_ESTOQUE = "estoque.txt";  // Nome do arquivo de estoque
    private ArrayList<Item> estoque = new ArrayList<>();  // Lista de itens no estoque

    // Construtor que carrega o estoque do arquivo ao iniciar
    public exercicio_6_1() {
        carregarEstoque();  // Chama o método para carregar o estoque
    }

    // Método para adicionar um item ao estoque
    public void adicionarItem(String nome, int quantidade) {
        estoque.add(new Item(nome, quantidade));  // Adiciona o item à lista de estoque
        salvarEstoque();  // Salva o estoque no arquivo
        System.out.println("Item adicionado com sucesso!");  // Confirmação para o usuário
    }

    // Método para remover um item do estoque
    public void removerItem(String nome) {
        // Remove o item que tem o nome igual ao fornecido (ignora maiúsculas/minúsculas)
        boolean removido = estoque.removeIf(item -> item.nome.equalsIgnoreCase(nome));
        
        // Se o item foi removido com sucesso
        if (removido) {
            salvarEstoque();  // Salva o estoque atualizado
            System.out.println("Item removido com sucesso!");  // Confirmação para o usuário
        } else {
            System.out.println("Item não encontrado!");  // Aviso de item não encontrado
        }
    }

    // Método para listar todos os itens no estoque
    public void listarItens() {
        if (estoque.isEmpty()) {
            System.out.println("Nenhum item no estoque.");  // Aviso caso o estoque esteja vazio
        } else {
            for (Item item : estoque) {  // Percorre a lista de itens
                System.out.println(item);  // Exibe cada item (chama toString do Item)
            }
        }
    }

    // Método para carregar o estoque do arquivo texto
    private void carregarEstoque() {
        // Tenta ler o arquivo de estoque
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_ESTOQUE))) {
            String linha;
            // Lê cada linha do arquivo até que não haja mais
            while ((linha = reader.readLine()) != null) {
                // Divide a linha em nome e quantidade
                String[] partes = linha.split(":");
                String nome = partes[0].trim();  // Pega o nome do item
                int quantidade = Integer.parseInt(partes[1].trim());  // Converte a quantidade para int
                estoque.add(new Item(nome, quantidade));  // Adiciona o item ao estoque
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de estoque não encontrado. Um novo será criado.");  // Aviso se o arquivo não existir
        } catch (IOException e) {
            System.out.println("Erro ao carregar estoque: " + e.getMessage());  // Tratamento de erro ao ler o arquivo
        }
    }

    // Método para salvar o estoque no arquivo texto
    private void salvarEstoque() {
        // Tenta escrever o estoque no arquivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_ESTOQUE))) {
            // Percorre todos os itens no estoque
            for (Item item : estoque) {
                writer.write(item.nome + ": " + item.quantidade);  // Escreve cada item no formato "nome: quantidade"
                writer.newLine();  // Pula para a próxima linha
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar estoque: " + e.getMessage());  // Tratamento de erro ao salvar o arquivo
        }
    }

    // Método principal (main) para executar o sistema de estoque
    public static void main(String[] args) {
        SistemaEstoque_6 sistema = new SistemaEstoque_6();  // Instancia o sistema de estoque
        Scanner scanner = new Scanner(System.in);  // Cria o scanner para entrada do usuário

        // Loop infinito para o menu do sistema
        while (true) {
            System.out.println("1. Adicionar Item");
            System.out.println("2. Remover Item");
            System.out.println("3. Listar Itens");
            System.out.println("4. Sair");

            int opcao = scanner.nextInt();  // Captura a opção escolhida pelo usuário
            scanner.nextLine();  // Limpa o buffer do scanner

            // Realiza a ação de acordo com a opção escolhida
            switch (opcao) {
                case 1:
                    // Adicionar item
                    System.out.println("Digite o nome do item:");
                    String nome = scanner.nextLine();  // Captura o nome do item
                    System.out.println("Digite a quantidade:");
                    int quantidade = scanner.nextInt();  // Captura a quantidade do item
                    sistema.adicionarItem(nome, quantidade);  // Adiciona o item ao estoque
                    break;
                case 2:
                    // Remover item
                    System.out.println("Digite o nome do item para remover:");
                    nome = scanner.nextLine();  // Captura o nome do item
                    sistema.removerItem(nome);  // Remove o item do estoque
                    break;
                case 3:
                    // Listar itens
                    sistema.listarItens();  // Exibe a lista de itens no estoque
                    break;
                case 4:
                    // Sair do sistema
                    System.exit(0);  // Encerra o programa
                default:
                    System.out.println("Opção inválida!");  // Exibe uma mensagem de erro se a opção for inválida
            }
        }
    }
}
