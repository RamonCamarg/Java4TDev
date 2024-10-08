package com.imoveis.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Administrador {
    private int id;
    private String nome;
    private String email;
    private String cpf;
    private String telefone;

}
