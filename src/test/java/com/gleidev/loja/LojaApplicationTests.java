package com.gleidev.loja;

import com.gleidev.loja.pagamento.PagSeguroService;
import com.gleidev.loja.pagamento.PaypalService;
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
        venda.setProduto("Camiseta branca básica");
        venda.setQuantidade(2);
        venda.setPricoUnitario(new BigDecimal("109.5"));

        //VendaService vendaService = new VendaService(new PagSeguroService("857db3dbbce149ab8943430f4d18bdf3"));
        //VendaService vendaService = new VendaService(new PaypalService("gleides", "123"));
        VendaService vendaService = new VendaService(new MockPagSeguroService());
        vendaService.registrar(venda,"4024007021546352");
    }

}
