import java.util.Scanner;  // Importa a classe Scanner para capturar entrada do usuário

// Classe de exceção personalizada para lidar com números inválidos
class ValorInvalidoException extends Exception {
    // Construtor que recebe a mensagem da exceção
    public ValorInvalidoException(String mensagem) {
        super(mensagem);  // Chama o construtor da classe Exception para definir a mensagem de erro
    }
}

public class exercicio_4_1 {

    // Método recursivo para calcular o fatorial de um número
    public static int fatorial(int n) throws ValorInvalidoException {
        // Verifica se o número é negativo, o que não é permitido para o cálculo do fatorial
        if (n < 0) {
            throw new ValorInvalidoException("Número inválido! O número deve ser positivo.");  // Lança uma exceção personalizada
        }
        // Caso base da recursão: o fatorial de 0 ou 1 é 1
        if (n == 0 || n == 1) {
            return 1;
        }
        // Chamada recursiva: fatorial de n é n vezes o fatorial de (n - 1)
        return n * fatorial(n - 1);
    }

    public static void main(String[] args) {
        // Cria um objeto Scanner para capturar a entrada do usuário
        Scanner scanner = new Scanner(System.in);

        try {
            // Pede ao usuário para digitar um número
            System.out.println("Digite um número para calcular o fatorial:");
            int numero = scanner.nextInt();  // Captura o número digitado

            // Calcula o fatorial do número inserido
            int resultado = fatorial(numero);

            // Exibe o resultado
            System.out.println("Fatorial de " + numero + " é: " + resultado);

        } catch (ValorInvalidoException e) {
            // Captura a exceção personalizada e exibe a mensagem correspondente
            System.out.println(e.getMessage());
        } catch (Exception e) {
            // Captura qualquer outra exceção inesperada e exibe a mensagem de erro
            System.out.println("Erro inesperado: " + e.getMessage());
        }

        // Fecha o objeto Scanner para liberar os recursos
        scanner.close();
    }
}
