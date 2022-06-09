package com.gleidev.loja.venda;


import java.math.BigDecimal;

public class Venda {
    private String produto;
    private Integer quantidade;
    private BigDecimal pricoUnitario;

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPricoUnitario() {
        return pricoUnitario;
    }

    public void setPricoUnitario(BigDecimal pricoUnitario) {
        this.pricoUnitario = pricoUnitario;
    }
}
