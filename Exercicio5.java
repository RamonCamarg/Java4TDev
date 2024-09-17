import java.util.InputMismatchException;
import java.util.Scanner;

// Exceção personalizada: Contato não encontrado
class ContatoNaoEncontradoException extends Exception {
    public ContatoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}

// Exceção personalizada: Agenda cheia
class AgendaCheiaException extends Exception {
    public AgendaCheiaException(String mensagem) {
        super(mensagem);
    }
}

// Classe Contato
class Contato {
    private String nome;
    private String telefone;

    public Contato(String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + ", Telefone: " + telefone;
    }
}

// Classe Agenda
public class Exercicio5 {
    private Contato[] contatos;
    private int quantidadeContatos;

    public Exercicio5() {
        this.contatos = new Contato[100]; // Limite de 100 contatos
        this.quantidadeContatos = 0;
    }

    // Método para adicionar um contato
    public void adicionarContato(String nome, String telefone) throws AgendaCheiaException {
        if (quantidadeContatos >= contatos.length) {
            throw new AgendaCheiaException("Erro: A agenda está cheia. Não é possível adicionar mais contatos.");
        }
        contatos[quantidadeContatos] = new Contato(nome, telefone);
        quantidadeContatos++;
        System.out.println("Contato adicionado com sucesso.");
    }

    // Método para remover um contato pelo nome
    public void removerContato(String nome) throws ContatoNaoEncontradoException {
        boolean contatoRemovido = false;
        for (int i = 0; i < quantidadeContatos; i++) {
            if (contatos[i].getNome().equalsIgnoreCase(nome)) {
                // Move todos os contatos para a esquerda, substituindo o removido
                for (int j = i; j < quantidadeContatos - 1; j++) {
                    contatos[j] = contatos[j + 1];
                }
                contatos[quantidadeContatos - 1] = null; // Limpa o último espaço
                quantidadeContatos--;
                contatoRemovido = true;
                System.out.println("Contato removido com sucesso.");
                break;
            }
        }
        if (!contatoRemovido) {
            throw new ContatoNaoEncontradoException("Erro: Contato com o nome '" + nome + "' não encontrado.");
        }
    }

    // Método para buscar um contato pelo nome
    public Contato buscarContato(String nome) throws ContatoNaoEncontradoException {
        for (int i = 0; i < quantidadeContatos; i++) {
            if (contatos[i].getNome().equalsIgnoreCase(nome)) {
                return contatos[i];
            }
        }
        throw new ContatoNaoEncontradoException("Erro: Contato com o nome '" + nome + "' não encontrado.");
    }

    // Método para listar todos os contatos
    public void listarContatos() {
        if (quantidadeContatos == 0) {
            System.out.println("Nenhum contato cadastrado.");
        } else {
            System.out.println("Lista de contatos:");
            for (int i = 0; i < quantidadeContatos; i++) {
                System.out.println(contatos[i].toString());
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Exercicio5 agenda = new Exercicio5();
        boolean continuar = true;

        while (continuar) {
            try {
                System.out.println("\nMenu:");
                System.out.println("1. Adicionar contato");
                System.out.println("2. Remover contato");
                System.out.println("3. Buscar contato");
                System.out.println("4. Listar contatos");
                System.out.println("5. Sair");
                System.out.print("Escolha uma opção: ");
                int opcao = scanner.nextInt();
                scanner.nextLine(); // Limpa o buffer

                switch (opcao) {
                    case 1:
                        System.out.print("Digite o nome do contato: ");
                        String nome = scanner.nextLine();
                        System.out.print("Digite o telefone do contato: ");
                        String telefone = scanner.nextLine();
                        agenda.adicionarContato(nome, telefone);
                        break;
                    case 2:
                        System.out.print("Digite o nome do contato a ser removido: ");
                        String nomeRemover = scanner.nextLine();
                        agenda.removerContato(nomeRemover);
                        break;
                    case 3:
                        System.out.print("Digite o nome do contato para busca: ");
                        String nomeBuscar = scanner.nextLine();
                        Contato contato = agenda.buscarContato(nomeBuscar);
                        System.out.println(contato.toString());
                        break;
                    case 4:
                        agenda.listarContatos();
                        break;
                    case 5:
                        continuar = false;
                        System.out.println("Encerrando o programa...");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (AgendaCheiaException | ContatoNaoEncontradoException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida. Por favor, insira valores corretos.");
                scanner.nextLine(); // Limpa o buffer
            }
        }

        scanner.close();
    }
}
