import java.util.InputMismatchException;
import java.util.Scanner;

// Classe base Operacao
abstract class Operacao {
    public abstract double calcular(double a, double b) throws ArithmeticException;
}

// Subclasse Soma
class Soma extends Operacao {
    @Override
    public double calcular(double a, double b) {
        return a + b;
    }
}

// Subclasse Subtracao
class Subtracao extends Operacao {
    @Override
    public double calcular(double a, double b) {
        return a - b;
    }
}

// Subclasse Multiplicacao
class Multiplicacao extends Operacao {
    @Override
    public double calcular(double a, double b) {
        return a * b;
    }
}

// Subclasse Divisao
class Divisao extends Operacao {
    @Override
    public double calcular(double a, double b) throws ArithmeticException {
        if (b == 0) {
            throw new ArithmeticException("Divisão por zero não permitida!");
        }
        return a / b;
    }
}

// Subclasse RaizQuadrada
class RaizQuadrada extends Operacao {
    @Override
    public double calcular(double a, double b) throws ArithmeticException {
        if (a < 0) {
            throw new ArithmeticException("Não é possível calcular a raiz quadrada de um número negativo!");
        }
        return Math.sqrt(a);
    }
}

public class Exercicio3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            try {
                System.out.println("\nEscolha a operação desejada:");
                System.out.println("1. Soma");
                System.out.println("2. Subtração");
                System.out.println("3. Multiplicação");
                System.out.println("4. Divisão");
                System.out.println("5. Raiz Quadrada");
                System.out.println("6. Sair");
                System.out.print("Opção: ");
                int opcao = scanner.nextInt();

                if (opcao == 6) {
                    continuar = false;
                    System.out.println("Saindo da calculadora...");
                    break;
                }

                Operacao operacao = null;
                double a = 0, b = 0;

                if (opcao >= 1 && opcao <= 5) {
                    if (opcao != 5) { // Para as operações que não são raiz quadrada
                        System.out.print("Digite o primeiro número: ");
                        a = scanner.nextDouble();
                        System.out.print("Digite o segundo número: ");
                        b = scanner.nextDouble();
                    } else {
                        System.out.print("Digite o número para calcular a raiz quadrada: ");
                        a = scanner.nextDouble();
                    }

                    // Escolhe a operação com base na opção
                    switch (opcao) {
                        case 1:
                            operacao = new Soma();
                            break;
                        case 2:
                            operacao = new Subtracao();
                            break;
                        case 3:
                            operacao = new Multiplicacao();
                            break;
                        case 4:
                            operacao = new Divisao();
                            break;
                        case 5:
                            operacao = new RaizQuadrada();
                            break;
                        default:
                            System.out.println("Opção inválida.");
                    }

                    // Executa a operação e exibe o resultado
                    if (operacao != null) {
                        double resultado = operacao.calcular(a, b);
                        System.out.println("Resultado: " + resultado);
                    }
                } else {
                    System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida. Por favor, insira números.");
                scanner.nextLine(); // Limpa a entrada incorreta
            } catch (ArithmeticException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
