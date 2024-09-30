import java.util.ArrayList;
import java.util.Scanner;

// Classe Funcionario para armazenar os detalhes de cada funcionário
class Funcionario {
    String nome;   // Atributo para armazenar o nome do funcionário
    int idade;     // Atributo para armazenar a idade do funcionário
    double salario;  // Atributo para armazenar o salário do funcionário

    // Construtor para inicializar os atributos do funcionário
    Funcionario(String nome, int idade, double salario) {
        this.nome = nome;
        this.idade = idade;
        this.salario = salario;
    }
}

// Classe principal que gerencia o cadastro de funcionários
public class exercicio_2_1 {
    public static void main(String[] args) {
        // Cria um ArrayList para armazenar os funcionários
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        // Scanner para capturar a entrada do usuário
        Scanner scanner = new Scanner(System.in);

        // Loop infinito para manter o menu em execução
        while (true) {
            // Exibe o menu de opções
            System.out.println("1. Adicionar Funcionário");
            System.out.println("2. Remover Funcionário");
            System.out.println("3. Listar Funcionários");
            System.out.println("4. Calcular Média Salarial");
            System.out.println("5. Sair");

            // Captura a opção escolhida pelo usuário
            int opcao = scanner.nextInt();

            // Usa um switch para tratar a opção selecionada
            switch (opcao) {
                case 1:
                    // Adicionar Funcionário
                    // Pede ao usuário para inserir os detalhes do funcionário
                    System.out.println("Digite o nome:");
                    String nome = scanner.next();
                    System.out.println("Digite a idade:");
                    int idade = scanner.nextInt();
                    System.out.println("Digite o salário:");
                    double salario = scanner.nextDouble();
                    // Adiciona um novo funcionário ao ArrayList
                    funcionarios.add(new Funcionario(nome, idade, salario));
                    break;

                case 2:
                    // Remover Funcionário
                    // Pede o nome do funcionário que deve ser removido
                    System.out.println("Digite o nome do funcionário para remover:");
                    nome = scanner.next();
                    // Remove o funcionário da lista se o nome for encontrado (ignora maiúsculas/minúsculas)
                    funcionarios.removeIf(func -> func.nome.equalsIgnoreCase(nome));
                    break;

                case 3:
                    // Listar Funcionários
                    // Percorre a lista de funcionários e exibe os detalhes de cada um
                    for (Funcionario func : funcionarios) {
                        System.out.println("Nome: " + func.nome + ", Idade: " + func.idade + ", Salário: " + func.salario);
                    }
                    break;

                case 4:
                    // Calcular Média Salarial
                    // Inicializa uma variável para somar os salários
                    double somaSalarios = 0;
                    // Percorre a lista de funcionários somando os salários
                    for (Funcionario func : funcionarios) {
                        somaSalarios += func.salario;
                    }
                    // Calcula a média salarial dividindo a soma pela quantidade de funcionários
                    double mediaSalarios = somaSalarios / funcionarios.size();
                    // Exibe a média salarial
                    System.out.println("Média Salarial: " + mediaSalarios);
                    break;

                case 5:
                    // Sair do programa
                    System.exit(0);

                // Não há um caso default, então qualquer entrada inválida será ignorada sem ação
            }
        }
    }
}
