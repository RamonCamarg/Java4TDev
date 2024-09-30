package com.example;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Eletronico extends Produto implements Transportavel{
    //atributo
    private double volume;
    private double peso;

    public Eletronico(String nome, double preco, double volume) {
        super(nome, preco);
        this.volume=volume;
    }

    //calcularPeso
    @Override
    public double calcularPeso(){
        this.peso = volume*150;
        return peso;
    }

    //calcularFrete
    @Override
    public double calcularFrete(){
        double valorFrete = peso*1;
        return valorFrete; 
    }
}
