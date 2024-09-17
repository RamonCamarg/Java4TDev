import java.util.Scanner;

public class Exercicio1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double[] notas = new double[4]; // Array para armazenar as 4 notas
        double soma = 0;

        // Coleta as notas das 4 disciplinas
        for (int i = 0; i < 4; i++) {
            System.out.print("Digite a nota da disciplina " + (i + 1) + ": ");
            notas[i] = scanner.nextDouble();
            soma += notas[i]; // Soma as notas para o cálculo da média
        }

        double media = soma / notas.length; // Calcula a média

        // Verifica se o aluno obteve nota maior que 9 em todas as disciplinas
        boolean todasNotasAcimaDeNove = true;
        for (double nota : notas) {
            if (nota <= 9) {
                todasNotasAcimaDeNove = false;
                break;
            }
        }

        // Aplica o bônus de 10% se o aluno obteve mais que 9 em todas as disciplinas
        if (todasNotasAcimaDeNove) {
            media *= 1.1;
        }

        // Exibe o status do aluno com base na média final
        if (media >= 7) {
            System.out.println("Aprovado com média: " + media);
        } else if (media >= 5 && media < 7) {
            System.out.println("Recuperação com média: " + media);
        } else {
            System.out.println("Reprovado com média: " + media);
        }

        scanner.close();
    }
}
