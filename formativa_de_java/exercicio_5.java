import java.util.ArrayList;
import java.util.Scanner;

// Exceções personalizadas
// Define uma exceção personalizada para quando um contato não é encontrado
class ContatoNaoEncontradoException extends Exception {
    public ContatoNaoEncontradoException(String mensagem) {
        super(mensagem);  // Passa a mensagem para a classe Exception
    }
}

// Define uma exceção personalizada para quando a agenda está cheia
class AgendaCheiaException extends Exception {
    public AgendaCheiaException(String mensagem) {
        super(mensagem);  // Passa a mensagem para a classe Exception
    }
}

// Classe Contato
// Define a classe Contato com dois atributos: nome e telefone
class Contato {
    String nome;
    String telefone;

    // Construtor para inicializar os atributos do contato
    Contato(String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;
    }
}

// Classe principal que gerencia a agenda telefônica
public class exercicio_5_1 {
    // Define o limite máximo de contatos na agenda
    private static final int LIMITE_CONTATOS = 100;

    // Utiliza um ArrayList para armazenar os contatos
    private ArrayList<Contato> contatos = new ArrayList<>();

    // Método para adicionar um contato à agenda
    public void adicionarContato(String nome, String telefone) throws AgendaCheiaException {
        // Verifica se a agenda atingiu o limite de contatos
        if (contatos.size() >= LIMITE_CONTATOS) {
            throw new AgendaCheiaException("A agenda está cheia!");  // Lança exceção se cheia
        }
        // Adiciona o contato ao ArrayList
        contatos.add(new Contato(nome, telefone));
        System.out.println("Contato adicionado com sucesso!");  // Confirma a adição
    }

    // Método para remover um contato da agenda
    public void removerContato(String nome) throws ContatoNaoEncontradoException {
        // Tenta remover o contato que tem o nome especificado
        boolean removido = contatos.removeIf(contato -> contato.nome.equalsIgnoreCase(nome));

        // Se nenhum contato foi removido, lança uma exceção personalizada
        if (!removido) {
            throw new ContatoNaoEncontradoException("Contato não encontrado!");
        }
        System.out.println("Contato removido com sucesso!");  // Confirma a remoção
    }

    // Método para buscar um contato pelo nome
    public Contato buscarContato(String nome) throws ContatoNaoEncontradoException {
        // Percorre a lista de contatos para encontrar o contato pelo nome
        for (Contato contato : contatos) {
            if (contato.nome.equalsIgnoreCase(nome)) {
                return contato;  // Retorna o contato se encontrado
            }
        }
        // Se não encontrar, lança exceção personalizada
        throw new ContatoNaoEncontradoException("Contato não encontrado!");
    }

    // Método para listar todos os contatos da agenda
    public void listarContatos() {
        // Verifica se há contatos na lista
        if (contatos.isEmpty()) {
            System.out.println("Nenhum contato cadastrado.");  // Informa que não há contatos
        } else {
            // Percorre e imprime os detalhes de cada contato
            for (Contato contato : contatos) {
                System.out.println("Nome: " + contato.nome + ", Telefone: " + contato.telefone);
            }
        }
    }

    // Método principal que contém o menu e as interações com o usuário
    public static void main(String[] args) {
        // Instancia a agenda telefônica
        exercicio_5_1 agenda = new exercicio_5_1();

        // Scanner para capturar entrada do usuário
        Scanner scanner = new Scanner(System.in);

        // Loop infinito para manter o programa rodando até o usuário escolher sair
        while (true) {
            // Mostra o menu de opções ao usuário
            System.out.println("1. Adicionar Contato");
            System.out.println("2. Remover Contato");
            System.out.println("3. Buscar Contato");
            System.out.println("4. Listar Contatos");
            System.out.println("5. Sair");
            System.out.println("Escolha uma das opções:");

            // Captura a opção escolhida pelo usuário
            int opcao = scanner.nextInt();
            scanner.nextLine();  // Limpa o buffer do scanner

            try {
                // Trata a escolha do usuário com base na opção selecionada
                switch (opcao) {
                    case 1:
                        // Captura os dados para adicionar um novo contato
                        System.out.println("Digite o nome:");
                        String nome = scanner.nextLine();
                        System.out.println("Digite o telefone:");
                        String telefone = scanner.nextLine();
                        // Adiciona o contato à agenda
                        agenda.adicionarContato(nome, telefone);
                        break;
                    case 2:
                        // Captura o nome do contato a ser removido
                        System.out.println("Digite o nome do contato para remover:");
                        nome = scanner.nextLine();
                        // Remove o contato da agenda
                        agenda.removerContato(nome);
                        break;
                    case 3:
                        // Captura o nome do contato a ser buscado
                        System.out.println("Digite o nome do contato para buscar:");
                        nome = scanner.nextLine();
                        // Busca o contato e imprime seus detalhes
                        Contato contato = agenda.buscarContato(nome);
                        System.out.println("Nome: " + contato.nome + ", Telefone: " + contato.telefone);
                        break;
                    case 4:
                        // Lista todos os contatos da agenda
                        agenda.listarContatos();
                        break;
                    case 5:
                        // Sai do programa
                        System.exit(0);
                    default:
                        // Trata opções inválidas
                        System.out.println("Opção inválida!");
                }
            } catch (AgendaCheiaException | ContatoNaoEncontradoException e) {
                // Captura e imprime exceções personalizadas
                System.out.println(e.getMessage());
            }
        }
    }
}
