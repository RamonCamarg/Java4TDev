import java.util.Scanner;

// Classe abstrata Operacao que define o método calcular que será implementado pelas subclasses
abstract class Operacao {
    // Método abstrato que será implementado para cada operação
    abstract double calcular(double a, double b) throws ArithmeticException;
}

// Implementação da soma
class Soma extends Operacao {
    // Implementa o método calcular para a soma de dois números
    double calcular(double a, double b) {
        return a + b;
    }
}

// Implementação da subtração
class Subtracao extends Operacao {
    // Implementa o método calcular para a subtração de dois números
    double calcular(double a, double b) {
        return a - b;
    }
}

// Implementação da multiplicação
class Multiplicacao extends Operacao {
    // Implementa o método calcular para a multiplicação de dois números
    double calcular(double a, double b) {
        return a * b;
    }
}

// Implementação da divisão
class Divisao extends Operacao {
    // Implementa o método calcular para a divisão, com verificação de divisão por zero
    double calcular(double a, double b) throws ArithmeticException {
        if (b == 0) {
            throw new ArithmeticException("Divisão por zero!");  // Lança uma exceção se o divisor for zero
        }
        return a / b;
    }
}

// Implementação da raiz quadrada
class RaizQuadrada extends Operacao {
    // Implementa o método calcular para a raiz quadrada, com verificação de número negativo
    double calcular(double a, double b) throws ArithmeticException {
        if (a < 0) {
            throw new ArithmeticException("Raiz quadrada de número negativo!");  // Lança uma exceção se o número for negativo
        }
        return Math.sqrt(a);  // Calcula a raiz quadrada do primeiro número
    }
}

// Classe principal que contém o menu e a lógica para operar a calculadora
public class exercicio_3_1 {
    public static void main(String[] args) {
        // Scanner para capturar entrada do usuário
        Scanner scanner = new Scanner(System.in);

        // Loop para exibir o menu até que o usuário decida sair
        while (true) {
            // Exibe o menu de operações
            System.out.println("1. Soma");
            System.out.println("2. Subtração");
            System.out.println("3. Multiplicação");
            System.out.println("4. Divisão");
            System.out.println("5. Raiz Quadrada");
            System.out.println("6. Sair");

            // Captura a escolha do usuário
            int escolha = scanner.nextInt();
            double a, b = 0;

            // Para todas as operações exceto raiz quadrada, pede dois números
            if (escolha != 5) {
                System.out.println("Digite o primeiro número:");
                a = scanner.nextDouble();
                System.out.println("Digite o segundo número:");
                b = scanner.nextDouble();
            } else {
                // Para raiz quadrada, só pede um número
                System.out.println("Digite o número:");
                a = scanner.nextDouble();
            }

            // Inicializa uma referência para a classe Operacao
            Operacao operacao = null;

            // Define a operação com base na escolha do usuário
            switch (escolha) {
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
                case 6:
                    // Sai do programa se o usuário escolher a opção 6
                    System.exit(0);
            }

            try {
                // Calcula e exibe o resultado da operação
                System.out.println("Resultado: " + operacao.calcular(a, b));
            } catch (ArithmeticException e) {
                // Trata exceções de operações inválidas, como divisão por zero ou raiz quadrada de número negativo
                System.out.println(e.getMessage());
            }
        }
    }
}
