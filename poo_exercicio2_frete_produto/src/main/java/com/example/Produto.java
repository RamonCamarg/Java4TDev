package com.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public abstract class Produto {
    //atributos
    private String nome;
    private double preco;

    //calcular peso
    public abstract double calcularPeso();

}
