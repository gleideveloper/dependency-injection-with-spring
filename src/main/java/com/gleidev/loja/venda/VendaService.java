package com.gleidev.loja.venda;

import com.gleidev.loja.pagamento.GatewayPagamento;
import com.gleidev.loja.pagamento.PagSeguroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class VendaService {
    //Injeção de dependência usando propriedade, mas devido ser privada torna-se dificil de ser usado nos testes unitário
    @Autowired
    private GatewayPagamento gatewayPagamento;

    public VendaService(){

    }

    //Injeção de dependência usando o construtor que obriga a instanciação da interface GatewayPagamento
    public VendaService(GatewayPagamento gatewayPagamento) {
        this.gatewayPagamento = gatewayPagamento;
    }
    //Injeção de dependência usando método
    @Autowired
    public void setGatewayPagamento(GatewayPagamento gatewayPagamento){
        this.gatewayPagamento = gatewayPagamento;

    }

    public void registrar(Venda venda, String numeroCartao){
        BigDecimal valorTotal = venda.getPricoUnitario().multiply(new BigDecimal(venda.getQuantidade()));
        System.out.printf("[Venda] Registrando venda de %s no valor total de %f...\n", venda.getProduto(), valorTotal);

        /*Forte acomplamento entre a clase VendaService com a classe PagSeguroService
        PagSeguroService pagSeguroService = new PagSeguroService("857db3dbbce149ab8943430f4d18bdf3");*/

        //Fraco acomplamento usando a interface por meio do construtor ou propriedade ou método
        gatewayPagamento.efetuarPagamento(numeroCartao, valorTotal);
    }
}
