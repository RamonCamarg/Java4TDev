package com.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Funcionario {
    // atributos
    private String nome;
    private int idade;
    private double salario;

    //método
    @Override 
    public String toString(){
        return "Nome "+nome +", Idade "+idade +", salário "+salario;
    }
}
