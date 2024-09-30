package br.com.diogotb;

import java.util.Scanner;

public class Exercicio4Recursao {
    int numero = -1;

    Scanner sc = new Scanner(System.in);

    // Calculo do Fatorial
    public long calculoFatorial(int n) {
        if (n == 0 || n == 1) {
            return 1;
        } else {
            return n * calculoFatorial(n - 1);
            // 5! = 5*4*3*2*1 = 5*4!
            // 4! = 4*3!
        }
    }

    // calcular o fatorial
    public void calculadora() throws Exception {
        System.out.println("Digite um nº para calcular o fatorial");
        numero = sc.nextInt();
        
        while (true) {
            if (numero < 0) {
                throw new Exception("o nº deve ser positivo");
            }
            try {
                System.out.println(calculoFatorial(numero));
            } catch (Exception e) {
                System.out.println("Nº Invalido");
                numero = sc.nextInt();
            }
        }
    }
}
