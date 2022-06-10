package com.gleidev.loja;

import com.gleidev.loja.venda.Venda;
import com.gleidev.loja.venda.VendaService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;

@SpringBootApplication
public class LojaApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(LojaApplication.class, args);

        VendaService vendaService = applicationContext.getBean(VendaService.class);

        Venda venda = new Venda();
        venda.setProduto("Camiseta branca básica");
        venda.setQuantidade(2);
        venda.setPricoUnitario(new BigDecimal("109.5"));

        vendaService.registrar(venda, "249645646521684");
    }

}
