package br.com.diogotb;

import java.util.Scanner;

public class Exercicio3CalculadoraAvancada {
    //atributos
    double a;
    double b;
    double resultado;
    int escolha; //escolha da operacao

    Scanner sc = new Scanner(System.in);

    //criar o métodas das operações
    //soma
    public double somar(double a, double b){
        resultado = a+b;
        return resultado;
    }
    //sub
    public double sub(double a, double b){
        resultado = a-b;
        return resultado;
    }
    //mult
    public double mult(double a, double b){
        resultado = a*b;
        return resultado;
    }
    //div
    public double div(double a, double b){
        try {
            resultado = a/b;
            
        } catch (ArithmeticException e) {
            System.err.println(e);
        }
        return resultado;
        
    }
    //raiz
    public double raiz(double a) throws Exception{
        if(a<0){
            throw new Exception("Não Existe Raiz de Nº Negativo");
        }
        try {
            resultado = Math.sqrt(a);
        } catch (Exception e) {
            System.err.println(e);
        }
        return resultado;
    }

    //Criar método para lançar o menu
    public void menu(){
        System.out.println("Calculadora");
        System.out.println("1. Soma");
        System.out.println("2. Subtração");
        System.out.println("3. Multiplicação");
        System.out.println("4. Divisão");
        System.out.println("5. Raiz Quadrada");
        System.out.println("6. Sair");

    }
    //Entrada de Dados
    public void entradaDados(){
        if (escolha >= 1 && escolha<5) {
            System.out.println("Digite o Valor a:");
            a = sc.nextDouble();
            System.out.println("Digite o Valor b:");
            b = sc.nextDouble();
        } else if( escolha == 5){
            System.out.println("Digite o Valor a:");
            a = sc.nextDouble();
        } else if (escolha ==6) {
            System.out.println("Saindo....");
        } else{
            System.out.println("Digite um Valor Válido");
        }
    }

    //Calculadora
    public void calculadora(){
        do {
            menu();
            try {
                escolha = sc.nextInt();
                switch (escolha) {
                    case 1:
                        entradaDados();
                        somar(a, b);
                        break;
                    case 2:
                        entradaDados();
                        sub(a, b);
                        break;
                    case 3:
                        entradaDados();
                        mult(a, b);
                        break;
                    case 4:
                        entradaDados();
                        div(a, b);
                        break;
                    case 5:
                        entradaDados();
                        raiz(a);
                        break;
                    case 6:
                        entradaDados();
                        break;
                    default:
                        entradaDados();
                        break;
                }                
            } catch (Exception e) {
               System.err.println(e);
            }
        } while (escolha !=6);
    }

}
