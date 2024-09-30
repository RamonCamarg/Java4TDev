package com.example;

import java.util.ArrayList;
import java.util.List;

public class Departamento {
    private List<Funcionario> funcionarios = new ArrayList<>();

    public void adicionarFuncionario(Funcionario funcionario) {
        funcionarios.add(funcionario);
    }

    public double calcularTotalSalarios() {
        double total = 0;
        for (Funcionario funcionario : funcionarios) {
            total += funcionario.calcularSalario();
        }
        return total;
    }
}

