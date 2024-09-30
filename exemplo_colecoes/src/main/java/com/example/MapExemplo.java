package com.example;

import java.util.HashMap;
import java.util.Map;

public class MapExemplo {
    private Map<String,Integer> nomesIdades;

    public MapExemplo() {
        nomesIdades = new HashMap<>();
    }

    //adicionar
    public void addNomeIdade(String nome, int idade){
        nomesIdades.put(nome, idade);
        System.out.println(nomesIdades.getOrDefault(nome,idade));
    }
    //listar
    public void listarNomesIdade(){
        System.out.println(nomesIdades);
    }
    //remove
    public void removerNomeIdade(String nome){
        nomesIdades.remove(nome);
    }
    //modificar valor
    public void modificarValor(String nome, int novoIdade){
        nomesIdades.replace(nome,novoIdade);
    }

}
