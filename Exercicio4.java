import java.util.InputMismatchException;
import java.util.Scanner;

// Exceção personalizada para valores inválidos
class ValorInvalidoException extends Exception {
    public ValorInvalidoException(String mensagem) {
        super(mensagem);
    }
}

public class Exercicio4 {

    // Método recursivo para calcular o fatorial
    public static long calcularFatorial(int n) throws ValorInvalidoException {
        if (n < 0) {
            throw new ValorInvalidoException("Erro: O número deve ser inteiro positivo.");
        }
        if (n == 0 || n == 1) {
            return 1; // Caso base: fatorial de 0 ou 1 é 1
        }
        return n * calcularFatorial(n - 1); // Chamada recursiva
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean entradaValida = false;

        while (!entradaValida) {
            try {
                System.out.print("Digite um número inteiro positivo para calcular o fatorial: ");
                int numero = scanner.nextInt();

                // Chama o método recursivo para calcular o fatorial
                long fatorial = calcularFatorial(numero);
                System.out.println("O fatorial de " + numero + " é: " + fatorial);
                entradaValida = true; // Sai do loop se não houver exceções
            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida. Por favor, insira um número inteiro.");
                scanner.nextLine(); // Limpa o buffer de entrada
            } catch (ValorInvalidoException e) {
                System.out.println(e.getMessage());
            }
        }

        scanner.close();
    }
}
