package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ArraysasListExemplo
 */
public class ArraysasListExemplo {
    private String[] nomes = {"Maria", "João", "Pedro"};
    private List<String> nomesList;

    public ArraysasListExemplo() {
        nomesList = new ArrayList<>(Arrays.asList(nomes));
    }
    //adicionar no Array
    public void addArray(String nome){
        try {
            nomes[3] = nome;
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    //adicionar no ArrayDinamico
    public void addNome(String nome){
        nomesList.add(nome);
        System.out.println("Nome Adiconado com sucesso");
    }
    //lista
    public void listarNomes(){
        System.out.println(nomesList);
    }

}