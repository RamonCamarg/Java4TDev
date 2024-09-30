package com.example;

import java.util.ArrayList;
import java.util.List;

public class CarrinhoDeCompras {
    private List<Produto> produtos = new ArrayList<>();

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    public double calcularTotal() {
        double total = 0;
        for (Produto produto : produtos) {
            total += produto.getPreco();
        }
        return total;
    }

    public double calcularFreteTotal() {
        double freteTotal = 0;
        for (Produto produto : produtos) {
            if (produto instanceof Transportavel) {
                freteTotal += ((Transportavel) produto).calcularFrete();
            }
        }
        return freteTotal;
    }
}

