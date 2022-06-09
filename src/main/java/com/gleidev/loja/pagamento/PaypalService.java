package com.gleidev.loja.pagamento;

import java.math.BigDecimal;

public class PaypalService implements GatewayPagamento{
    private String usuario;
    private String senha;

    public PaypalService(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }

    @Override
    public void efetuarPagamento(String numeroCartao, BigDecimal valor){
        System.out.printf("[PagSeguro] Usando usuario: %s\n", this.usuario);
        System.out.printf("[PagSeguro] Cobrando %f do cartão %s...\n", valor, numeroCartao);
    }
}
