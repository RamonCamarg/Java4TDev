package com.example;

public abstract class Produto {
    private String nome;
    private double preco;

    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public abstract double calcularPeso();

    public double getPreco() {
        return preco;
    }

    public String getNome() {
        return nome;
    }

    public class Eletronico extends Produto {
        public Eletronico(String nome, double preco) {
            super(nome, preco);
        }
    
        @Override
        public double calcularPeso() {
            return 1.5; // peso fixo ou cálculo baseado em alguma regra
        }
    }
    
    public class Roupa extends Produto {
        public Roupa(String nome, double preco) {
            super(nome, preco);
        }
    
        @Override
        public double calcularPeso() {
            return 0.5; // peso fixo ou cálculo baseado em alguma regra
        }
    }
    
    public class Livro extends Produto {
        public Livro(String nome, double preco) {
            super(nome, preco);
        }
    
        @Override
        public double calcularPeso() {
            return 1.0; // peso fixo ou cálculo baseado em alguma regra
        }
    }
    
}

