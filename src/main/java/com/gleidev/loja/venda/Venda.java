package com.gleidev.loja.venda;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class Venda {
    private String produto;
    private Integer quantidade;
    private BigDecimal pricoUnitario;
}
