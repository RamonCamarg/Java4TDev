import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class exercicio_1_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double[] notas = new double[4];
        double soma = 0.0;

        // Coleta o nome do aluno
        System.out.println("Digite o nome do aluno: ");
        String nome = scanner.nextLine();

        // Coleta o ano do aluno
        System.out.println("Digite o ano do aluno: ");
        String ano = scanner.nextLine();

        // Coletar as notas
        for (int i = 0; i < notas.length; i++) {
            System.out.println("Digite a nota da disciplina " + (i + 1) + ": ");
            notas[i] = scanner.nextDouble();
            soma += notas[i];
        }

        // Calcular a média
        double media = soma / notas.length;

        // Verificar se há bônus
        boolean todasAcimaDeNove = true;
        for (double nota : notas) {
            if (nota <= 9) {
                todasAcimaDeNove = false;
                break;
            }
        }
        if (todasAcimaDeNove) {
            media += media * 0.1;  // 10% de bônus
        }

        // Determinar o resultado final
        String resultado;
        if (media >= 7) {
            resultado = "Aprovado";
        } else if (media >= 5) {
            resultado = "Recuperação";
        } else {
            resultado = "Reprovado";
        }

        // Exibir o resultado no console
        System.out.println("Resultado: " + resultado + ". Media: " + media);

        // Salvar os dados em um arquivo de texto
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("alunos_meida.txt", true))) {
            writer.write("Nome: " + nome);
            writer.newLine();
            writer.write("Ano: " + ano);
            writer.newLine();
            writer.write("Media: " + media);
            writer.newLine();
            writer.write("Resultado: " + resultado);
            writer.newLine();
            writer.write("-----------------------");
            writer.newLine();
            System.out.println("Dados salvos com sucesso no arquivo 'aprovacao_aluno.txt'.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados: " + e.getMessage());
        }

        scanner.close();
    }
}
