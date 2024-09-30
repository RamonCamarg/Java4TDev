package com.example;

import java.util.ArrayList;
import java.util.List;

public class Curso {
    private String nomeCurso;
    private List<Aluno> alunos;
    private Professor professor;

    public Curso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
        alunos = new ArrayList<>();
    }

    // adiconar Professor na curso
    public void addProf(Professor professor){
        this.professor = professor; 
    }

    //Adicionar Alunos
    public void addAluno(Aluno aluno){
        alunos.add(aluno);
    }

    //lançar notas
    public void lancarNotas(String nomeAluno, double notaAluno){
        for (Aluno aluno : alunos) {
            if (aluno.getNome().equalsIgnoreCase(nomeAluno)) {
                aluno.setNota(notaAluno);
                System.out.println("Nota inserida com Sucesso");
                return;
            }
        }
        System.out.println("Aluno Não Encontrado");
    }
    //exibir nota final de todos os alunos
    public void resultadoFinal(){
        for (Aluno aluno : alunos) {
            System.out.println(aluno.exibirInfo());
            aluno.avaliadDesempenho();
        }
    }
}
