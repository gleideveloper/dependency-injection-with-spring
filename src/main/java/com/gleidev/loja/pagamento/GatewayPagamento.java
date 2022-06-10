package com.gleidev.loja.pagamento;

import java.math.BigDecimal;


public interface GatewayPagamento {
    void efetuarPagamento(String numeroCartao, BigDecimal valor);
}
