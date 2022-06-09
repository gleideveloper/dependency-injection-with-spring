package com.gleidev.loja;

import com.gleidev.loja.pagamento.GatewayPagamento;
import com.gleidev.loja.pagamento.PagSeguroService;

import java.math.BigDecimal;

public class MockPagSeguroService implements GatewayPagamento {
    @Override
    public void efetuarPagamento(String numeroCartao, BigDecimal valor) {
        System.out.println("Não faz nada");
    }
}
