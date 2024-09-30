package com.example;

import java.util.List;

public class Aluno {
    private String nome;
    private List<Double> notas;

    public Aluno(String nome, List<Double> notas) {
        this.nome = nome;
        this.notas = notas;
    }

    public String getNome() {
        return nome;
    }

    public List<Double> getNotas() {
        return notas;
    }

    public double calcularMedia() {
        return notas.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }

    @Override
    public String toString() {
        return "Aluno: " + nome + ", Média: " + calcularMedia();
    }
}

