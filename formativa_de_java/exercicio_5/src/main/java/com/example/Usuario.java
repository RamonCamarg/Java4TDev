package com.example;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nome;
    private String cpf;
    private List<ItemBiblioteca> itensEmprestados = new ArrayList<>();

    public Usuario(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public void emprestar(ItemBiblioteca item) {
        item.emprestar();
        itensEmprestados.add(item);
    }

    public void devolver(ItemBiblioteca item) {
        itensEmprestados.remove(item);
    }
}

