package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.util.List;

public class App {
    public static void main(String[] args) {
        try {
            // Criando cliente HTTP
            HttpClient client = HttpClient.newHttpClient();

            // Criando a requisição para a API
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:3000/usuarios"))
                    .build();

            // Enviando a requisição e obtendo a resposta
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Verificando o status da resposta
            if (response.statusCode() == 200) {
                // Mapeando o JSON para uma lista de objetos Usuario
                ObjectMapper mapper = new ObjectMapper();
                List<Usuario> usuarios = mapper.readValue(response.body(), new TypeReference<List<Usuario>>() {});

                // Processar os dados
                exibirDadosUsuarios(usuarios);
            } else {
                System.out.println("Erro ao acessar a API. Status: " + response.statusCode());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void exibirDadosUsuarios(List<Usuario> usuarios) {
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário encontrado.");
            return;
        }

        int totalUsuarios = usuarios.size();
        int somaIdades = 0;

        Usuario usuarioMaisVelho = usuarios.get(0);
        Usuario usuarioMaisNovo = usuarios.get(0);

        for (Usuario usuario : usuarios) {
            System.out.println("Nome: " + usuario.getNome() + ", Idade: " + usuario.getIdade() + ", Cidade: " + usuario.getCidade());

            // Calculando a soma das idades
            somaIdades += usuario.getIdade();

            // Verificando o mais velho e o mais novo
            if (usuario.getIdade() > usuarioMaisVelho.getIdade()) {
                usuarioMaisVelho = usuario;
            }
            if (usuario.getIdade() < usuarioMaisNovo.getIdade()) {
                usuarioMaisNovo = usuario;
            }
        }

        double mediaIdades = (double) somaIdades / totalUsuarios;

        System.out.println("\nNúmero total de usuários: " + totalUsuarios);
        System.out.println("Média de idades: " + mediaIdades);
        System.out.println("Usuário mais velho: " + usuarioMaisVelho.getNome() + " (" + usuarioMaisVelho.getIdade() + " anos)");
        System.out.println("Usuário mais novo: " + usuarioMaisNovo.getNome() + " (" + usuarioMaisNovo.getIdade() + " anos)");
    }
}

