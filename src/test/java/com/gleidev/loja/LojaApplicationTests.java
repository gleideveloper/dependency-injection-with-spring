package com.gleidev.loja;

import com.gleidev.loja.venda.Venda;
import com.gleidev.loja.venda.VendaService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class LojaApplicationTests {

    @Test
    void registrarVenda() {
        Venda venda = new Venda();
        venda.setProduto("Camiseta branca básic");
        venda.setQuantidade(2);
        venda.setPricoUnitario(new BigDecimal("109.50"));

        VendaService vendaService = new VendaService();
        vendaService.registrar(venda,"4024007021546352");
    }

}
