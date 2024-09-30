package com.example;

public class Main {
    public static void main(String[] args) {
        AlunoIOReader dados = new AlunoIOReader();
        System.out.println("A média da Turma é "+dados.mediaAlunos());
        System.out.println(dados.alunoComMaiorNota());
        System.out.println(dados.alunoComMenorNota());
    }
}