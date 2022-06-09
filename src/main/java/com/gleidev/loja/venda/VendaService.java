package com.gleidev.loja.venda;

import com.gleidev.loja.pagamento.GatewayPagamento;
import com.gleidev.loja.pagamento.PagSeguroService;

import java.math.BigDecimal;

public class VendaService {

    private final GatewayPagamento gatewayPagamento;
    //Injeção de dependência usando o construtor
    public VendaService(GatewayPagamento gatewayPagamento) {
        this.gatewayPagamento = gatewayPagamento;
    }

    public void registrar(Venda venda, String numeroCartao){
        BigDecimal valorTotal = venda.getPricoUnitario().multiply(new BigDecimal(venda.getQuantidade()));
        System.out.printf("[Venda] Registrando venda de %s no valor total de %f...\n", venda.getProduto(), valorTotal);

        //Forte acomplamento entre a clase VendaService com a classe PagSeguroService
        //PagSeguroService pagSeguroService = new PagSeguroService("857db3dbbce149ab8943430f4d18bdf3");

        //Fraco acomplamento usando a interface por meio do construtor
        gatewayPagamento.efetuarPagamento(numeroCartao, valorTotal);
    }
}
