package com.example;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Aluno extends Pessoa implements Avaliavel{
    //atributos
    private String matricula;
    private double nota;

    //ctor
    public Aluno(String nome, String cpf, String matricula) {
        super(nome, cpf);
        this.matricula = matricula;
        this.nota = 0.0;
    }

    //Polimorfismo - sobreescrita
    @Override
    public String exibirInfo(){
        super.exibirInfo();
        return " Matricula: "+matricula +" Nota: "+nota;
    }

    @Override
    public void avaliadDesempenho() {
        if (nota >=7) {
            System.out.println("Aluno Aprovado");
        }else if(nota>=5 && nota<7){
            System.out.println("Aluno de Recuperação");
        }else{
            System.out.println("Aluno Reprovado");
        }
    }

    

}
