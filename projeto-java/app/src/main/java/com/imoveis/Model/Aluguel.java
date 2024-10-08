package com.imoveis.Model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Aluguel {
    private Date data_inicio;
    private Date data_fim;
    private Usuario usuario;
    private Imovel imovel;
}
