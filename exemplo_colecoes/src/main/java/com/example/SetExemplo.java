package com.example;

import java.util.HashSet;
import java.util.Set;

public class SetExemplo {
    //atributo
    private Set<String> nomes;
    //cons
    public SetExemplo() {
        nomes = new HashSet<>();
    }
    //adicionar
    public void addNome(String nome){
        nomes.add(nome);
        System.out.println(nomes.hashCode());
    }
    //listar
    public void listarNomes(){
        System.out.println(nomes);
    }
    //remover
    public void removerNome(String nome){
        try {
            nomes.remove(nome);
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    //atualizar/modificar
    
}
