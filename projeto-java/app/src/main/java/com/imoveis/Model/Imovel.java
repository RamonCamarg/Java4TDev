package com.imoveis.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Imovel {
    private String codigo_id;
    private String cidade;
    private String estado;
    private String endereco;
    private double valor_aluguel;
    private String descricao;
    private String status;

}
