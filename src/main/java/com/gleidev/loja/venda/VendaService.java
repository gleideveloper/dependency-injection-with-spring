package com.gleidev.loja.venda;

import com.gleidev.loja.pagamento.PagSeguroService;

import java.math.BigDecimal;

public class VendaService {

    public void registrar(Venda venda, String numeroCartao){
        BigDecimal valorTotal = venda.getPricoUnitario().multiply(new BigDecimal(venda.getQuantidade()));
        System.out.print("[Venda] Registrando venda de %s no valor total de %f...\n");

        //Forte acomplamento entre a clase VendaService com a classe PagSeguroService
        PagSeguroService pagSeguroService = new PagSeguroService("857db3dbbce149ab8943430f4d18bdf3");
        pagSeguroService.efetuarPagamento(numeroCartao, valorTotal);
    }
}
