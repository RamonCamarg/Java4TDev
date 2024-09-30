package com.example;

public class Main {
    public static void main(String[] args) {
        ListExemplo list = new ListExemplo();
        list.addNome("Maria");
        list.addNome("João");
        list.addNome("Pedro");
        list.listarNomes();
        list.modificarNome("Pedro", "Maria");
        list.listarNomes();
        list.removerNome("Maria");
        list.listarNomes();
        System.out.println("==========");
        SetExemplo set = new SetExemplo();
        set.addNome("Maria");
        set.addNome("João");
        set.addNome("Pedro");
        set.listarNomes();
        //adicionar Maria
        set.addNome("Maria");
        set.listarNomes();
        set.removerNome("Maria");
        set.listarNomes();
        System.out.println("========");
        MapExemplo map = new MapExemplo();
        map.addNomeIdade("Maria",25);
        map.addNomeIdade("João", 30);
        map.addNomeIdade("Pedro", 40);
        map.listarNomesIdade();
        map.modificarValor("Pedro",35);
        map.listarNomesIdade();
        map.removerNomeIdade("Maria");
        map.listarNomesIdade();
        System.out.println("===============");
        ArraysasListExemplo arraysAsList = new ArraysasListExemplo();
        arraysAsList.addArray("Celso");//erro
        arraysAsList.addNome("Celso");
        arraysAsList.listarNomes();

    }
}