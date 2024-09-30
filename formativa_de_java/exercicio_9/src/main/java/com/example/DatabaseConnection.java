package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/seu_banco_de_dados"; // Substitua pelo seu URL
    private static final String USER = "seu_usuario"; // Substitua pelo seu usuário
    private static final String PASSWORD = "sua_senha"; // Substitua pela sua senha

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
