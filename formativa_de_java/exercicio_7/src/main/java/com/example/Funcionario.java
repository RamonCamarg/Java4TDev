package com.example;

public abstract class Funcionario {
    private String nome;
    private double salarioBase;

    public Funcionario(String nome, double salarioBase) {
        this.nome = nome;
        this.salarioBase = salarioBase;
    }

    public abstract double calcularSalario();

    public String getNome() {
        return nome;
    }

    public class FuncionarioAssalariado extends Funcionario {
        public FuncionarioAssalariado(String nome, double salarioBase) {
            super(nome, salarioBase);
        }
    
        @Override
        public double calcularSalario() {
            return super.salarioBase;
        }
    }
    
    public class FuncionarioHorista extends Funcionario {
        private int horasTrabalhadas;
        private double valorHora;
    
        public FuncionarioHorista(String nome, double valorHora, int horasTrabalhadas) {
            super(nome, valorHora * horasTrabalhadas);
            this.horasTrabalhadas = horasTrabalhadas;
            this.valorHora = valorHora;
        }
    
        @Override
        public double calcularSalario() {
            return valorHora * horasTrabalhadas;
        }
    }
    
}

