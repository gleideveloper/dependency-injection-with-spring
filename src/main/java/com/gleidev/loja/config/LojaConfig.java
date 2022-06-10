package com.gleidev.loja.config;

import com.gleidev.loja.pagamento.PagSeguroService;
import com.gleidev.loja.pagamento.PaypalService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class LojaConfig {

    @Bean
    public PagSeguroService pagSeguroService() {
        return new PagSeguroService("857db3dbbce149ab8943430f4d18bdf3");
    }

    @Primary
    @Bean
    public PaypalService paypalService() {
        return new PaypalService("gleides", "123");
    }
}
