package com.example;

import java.io.IOException;
import java.util.List;

public class App {
    public static void main(String[] args) {
        String caminhoArquivo = "caminho/do/seu/arquivo.txt";  // Atualize com o caminho real do arquivo

        ProcessadorNotas processador = new ProcessadorNotas();

        try {
            // Ler arquivo e obter lista de alunos
            List<Aluno> alunos = processador.lerArquivo(caminhoArquivo);

            // Exibir alunos e suas médias
            for (Aluno aluno : alunos) {
                System.out.println(aluno);
            }

            // Obter aluno com maior média
            Aluno alunoMaiorMedia = processador.obterAlunoComMaiorMedia(alunos);
            System.out.println("\nAluno com maior média: " + alunoMaiorMedia.getNome() + " com média " + alunoMaiorMedia.calcularMedia());

            // Obter aluno com menor média
            Aluno alunoMenorMedia = processador.obterAlunoComMenorMedia(alunos);
            System.out.println("Aluno com menor média: " + alunoMenorMedia.getNome() + " com média " + alunoMenorMedia.calcularMedia());

            // Calcular média geral da turma
            double mediaGeral = processador.calcularMediaGeral(alunos);
            System.out.println("Média geral da turma: " + mediaGeral);

        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}
