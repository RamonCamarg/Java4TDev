package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class AlunoIOReader {
    String arquivo = "C:\\Users\\DevTarde\\Documents\\Diogo\\Tarde\\JavaProjectsTarde\\dados_exercicio1_leitura_arquivos\\arquivo.txt";
    List<Aluno> alunos;
    double mediaTurma = 0;
    //construtor
    public AlunoIOReader() {
        alunos = new ArrayList<>();
        readerFileIO();
    }
    //ler arquivo
    public void readerFileIO(){
        try (BufferedReader br = new BufferedReader(
            new FileReader(arquivo))) {
            String linha="";
            while ((linha=br.readLine()) != null) {
                String[] conteudo = linha.split(",");
                Aluno aluno=new Aluno(
                    conteudo[0],
                    Double.parseDouble(conteudo[1]),
                    Double.parseDouble(conteudo[2]),
                    Double.parseDouble(conteudo[3])
                );
                alunos.add(aluno);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //calcular média da turma
    public double mediaAlunos(){
        for (Aluno aluno : alunos) {
            mediaTurma += aluno.mediaNotas();
        }
        mediaTurma/=alunos.size();
        return mediaTurma;
    }
    //aluno com maior nota
    public String alunoComMaiorNota(){
        String nomeAluno = "";
        double maiorNota=0;
        for (Aluno aluno : alunos) {
            if (maiorNota<aluno.maiorNota()) {
                maiorNota = aluno.maiorNota();
                nomeAluno = aluno.getNome();
            }
        }
        return "O aluno "+nomeAluno+" teve a maior Nota:"+maiorNota;
    }

    //aluno com menor nota
    public String alunoComMenorNota(){
        String nomeAluno = "";
        double menorNota=10;
        for (Aluno aluno : alunos) {
            if (menorNota>aluno.menorNota()) {
                menorNota = aluno.menorNota();
                nomeAluno = aluno.getNome();
            }
        }
        return "O aluno "+nomeAluno+" teve a menor Nota:"+menorNota;
    }
}
