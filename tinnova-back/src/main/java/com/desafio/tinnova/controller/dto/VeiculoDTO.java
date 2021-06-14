package com.desafio.tinnova.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VeiculoDTO {

    private String veiculo;

    private String marca;

    private int ano;

    private String descricao;

    private boolean vendido;

    private Date created;

    private Date updated;
}
