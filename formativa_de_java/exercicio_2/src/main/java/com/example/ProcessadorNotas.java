package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProcessadorNotas {
    
    public List<Aluno> lerArquivo(String caminhoArquivo) throws IOException {
        List<Aluno> alunos = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");
                String nome = partes[0];
                List<Double> notas = new ArrayList<>();
                
                for (int i = 1; i < partes.length; i++) {
                    notas.add(Double.parseDouble(partes[i]));
                }
                
                alunos.add(new Aluno(nome, notas));
            }
        }
        
        return alunos;
    }

    public Aluno obterAlunoComMaiorMedia(List<Aluno> alunos) {
        return alunos.stream().max((a1, a2) -> Double.compare(a1.calcularMedia(), a2.calcularMedia())).orElse(null);
    }

    public Aluno obterAlunoComMenorMedia(List<Aluno> alunos) {
        return alunos.stream().min((a1, a2) -> Double.compare(a1.calcularMedia(), a2.calcularMedia())).orElse(null);
    }

    public double calcularMediaGeral(List<Aluno> alunos) {
        return alunos.stream().mapToDouble(Aluno::calcularMedia).average().orElse(0.0);
    }
}

