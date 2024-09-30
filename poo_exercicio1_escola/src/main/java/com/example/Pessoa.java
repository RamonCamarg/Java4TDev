package com.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class Pessoa {
    //atributos
    private String nome;
    private String cpf;

    //exibirInformacoes()
    public String exibirInfo(){
        return "Nome "+nome+", CPF "+cpf;
    }
}
