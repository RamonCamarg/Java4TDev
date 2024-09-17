import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class Funcionario {
    String nome;
    int idade;
    double salario;

    public Funcionario(String nome, int idade, double salario) {
        this.nome = nome;
        this.idade = idade;
        this.salario = salario;
    }
}

public class Exercicio2 {
    private static ArrayList<Funcionario> funcionarios = new ArrayList<>();

    // Método para adicionar um novo funcionário
    public static void adicionarFuncionario(String nome, int idade, double salario) {
        Funcionario novoFuncionario = new Funcionario(nome, idade, salario);
        funcionarios.add(novoFuncionario);
        System.out.println("Funcionário " + nome + " adicionado com sucesso.");
    }

    // Método para remover um funcionário pelo nome
    public static void removerFuncionario(String nome) {
        try {
            boolean encontrado = false;
            for (Funcionario func : funcionarios) {
                if (func.nome.equalsIgnoreCase(nome)) {
                    funcionarios.remove(func);
                    System.out.println("Funcionário " + nome + " removido com sucesso.");
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                System.out.println("Funcionário " + nome + " não encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao tentar remover o funcionário.");
        }
    }

    // Método para listar todos os funcionários
    public static void listarFuncionarios() {
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
        } else {
            System.out.println("Lista de funcionários:");
            for (Funcionario func : funcionarios) {
                System.out.println("Nome: " + func.nome + ", Idade: " + func.idade + ", Salário: " + func.salario);
            }
        }
    }

    // Método para calcular a média salarial dos funcionários
    public static void calcularMediaSalarial() {
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado para calcular a média salarial.");
        } else {
            double somaSalarios = 0;
            for (Funcionario func : funcionarios) {
                somaSalarios += func.salario;
            }
            double mediaSalarial = somaSalarios / funcionarios.size();
            System.out.println("A média salarial dos funcionários é: " + mediaSalarial);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            try {
                System.out.println("\nGerenciamento de Cadastro de Funcionários");
                System.out.println("1. Adicionar Funcionário");
                System.out.println("2. Remover Funcionário");
                System.out.println("3. Listar Funcionários");
                System.out.println("4. Calcular Média Salarial");
                System.out.println("5. Sair");
                System.out.print("Escolha uma opção: ");
                int opcao = scanner.nextInt();
                scanner.nextLine(); // Consumir a quebra de linha

                switch (opcao) {
                    case 1:
                        System.out.print("Digite o nome do funcionário: ");
                        String nome = scanner.nextLine();
                        System.out.print("Digite a idade do funcionário: ");
                        int idade = scanner.nextInt();
                        System.out.print("Digite o salário do funcionário: ");
                        double salario = scanner.nextDouble();
                        adicionarFuncionario(nome, idade, salario);
                        break;
                    case 2:
                        System.out.print("Digite o nome do funcionário a ser removido: ");
                        String nomeRemover = scanner.nextLine();
                        removerFuncionario(nomeRemover);
                        break;
                    case 3:
                        listarFuncionarios();
                        break;
                    case 4:
                        calcularMediaSalarial();
                        break;
                    case 5:
                        continuar = false;
                        System.out.println("Encerrando o programa.");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro de entrada. Tente novamente.");
                scanner.nextLine(); // Consumir a entrada inválida
            }
        }

        scanner.close();
    }
}
