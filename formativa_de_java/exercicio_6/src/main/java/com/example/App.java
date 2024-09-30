package com.example;

import java.util.List;

public class App {
    public static void main(String[] args) {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        
        List<Produto> produtos = produtoDAO.listarProdutos();
        
        System.out.println("Produtos:");
        for (Produto produto : produtos) {
            System.out.printf("Nome: %s, Preço: %.2f%n", produto.getNome(), produto.getPreco());
        }
        
        Produto maisCaro = produtoDAO.produtoMaisCaro(produtos);
        Produto maisBarato = produtoDAO.produtoMaisBarato(produtos);
        double mediaPrecos = produtoDAO.calcularMediaPrecos(produtos);
        
        System.out.printf("Produto mais caro: %s (%.2f)%n", maisCaro != null ? maisCaro.getNome() : "N/A", maisCaro != null ? maisCaro.getPreco() : 0.0);
        System.out.printf("Produto mais barato: %s (%.2f)%n", maisBarato != null ? maisBarato.getNome() : "N/A", maisBarato != null ? maisBarato.getPreco() : 0.0);
        System.out.printf("Média de preços: %.2f%n", mediaPrecos);
    }
}
