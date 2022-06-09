package com.gleidev.loja.pagamento;

import java.math.BigDecimal;

public class PagSeguroService implements GatewayPagamento {
    private String token;

    public PagSeguroService(String token) {
        this.token = token;
    }
    @Override
    public void efetuarPagamento(String numeroCartao, BigDecimal valor){
        System.out.printf("[PagSeguro] Usando token: %s\n", token);
        System.out.printf("[PagSeguro] Cobrando %f do cartão %s...\n", valor, numeroCartao);
    }
}
