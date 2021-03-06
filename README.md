# dependency-injection-with-spring
fonte: https://campuscode.com.br/conteudos/s-o-l-i-d-principio-de-inversao-de-dependencia
## Problema: Exemplo do diagrama abaixo fere os dois princípios do SOLID, que são:

### <a> Os Princípios do SOLID — OCP Princípio aberto-fechado</a>
O princípio de Aberto/Fechado propõe que entidades (classes, funções, módulos, etc.) devem ser abertas para extensão, mas fechadas para modificação.

* Aberto para extensão significa que, ao receber um novo requerimento, é possível adicionar um novo comportamento.
* Fechado para modificação significa que, para introduzir um novo comportamento (extensão), não é necessário modificar o código existente.
### <a>Os Princípios do SOLID — DIP Princípio de inversão de dependência</a>
O Princípio de Inversão de Dependência possui duas definições:
* (1) módulos de alto nível não devem depender de módulos de baixo nível e ambos devem depender de abstrações; e -
* (2) abstrações não devem depender de detalhes, mas detalhes devem depender de abstrações.

### Forte acoplamento entre classes
Exemplo no diagram de corte forte acoplamento entre a clase VendaService com a classe PagSeguroService, não sendo uma boa prática, sem o uso de interface.

![Alt text](src/main/resources/modelagem/AcomplamentoForte.png?raw=true "Forte Acomplamento")

* A classe VendaService é responsável por instanciar suas dependências, causando um forte acoplamento.
* A classe VendaService <b>conhece a dependência concreta</b> PagSeguroService.
* Se a classe PagSeguroService muda, é <b>preciso mudar a classe VendaService.</b>
* A classe VendaService é responsável por instanciar suas dependências, causando um forte acoplamento com pontos de alteração.
```java
public class VendaService {

    public void registrar(Venda venda, String numeroCartao){
        BigDecimal valorTotal = venda.getPricoUnitario().multiply(new BigDecimal(venda.getQuantidade()));
        System.out.print("[Venda] Registrando venda de %s no valor total de %f...\n");

        //Forte acomplamento entre a clase VendaService com a classe PagSeguroService
        PagSeguroService pagSeguroService = new PagSeguroService("857db3dbbce149ab8943430f4d18bdf3");
        pagSeguroService.efetuarPagamento(numeroCartao, valorTotal);
    }
}
````
## 1ª Solução: Injeção de dependência por meio do construtor

### Inversão de controle, Injeção de dependência

<b>acoplamento fraco.</b>
* A classe VendaService <b>não conhece a dependência concreta</b>
* Se a classe concreta mudar, <b>a classe VendaService não muda</b>, e só é alterado em um único lugar.

O exemplo abaixo temos um <b>fraco acoplamento</b> entre esse dois serviços, com o uso da <b> interface GatewayPagamento.</b>

```java
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
````

É a injeção de dependência chamada pelo construtor através de um upcasting, onde minha classe mais especifica é instanciada no contrustor da classe VendaService.

Se a própria classe VendaService não deve ser responsável por instanciar suas dependências.

## 2ª Solução: Injeção de dependência por meio do Conteiner do Spring (construtor ou propriedade ou método)

### Inversão de controle

É um padrão de desenvolvimento que consiste em <b>retirar da classe a responsabilidade de instanciar suas dependências.</b>

![Alt text](src/main/resources/modelagem/DependencyInjectionBySpring.png?raw=true "Fraco Acomplamento")

Classe config com anotação @Bean: As instancias dos objetos são definidos na classe config para serem automaticamente instanciados pelo framework spring.
```java
@Configuration
public class LojaConfig {

    @Bean
    public PagSeguroService pagSeguroService() {
        return new PagSeguroService("857db3dbbce149ab8943430f4d18bdf3");
    }

    //A anotação @Primary serve para definir qual instancia tem prioridade.
    @Primary
    @Bean
    public PaypalService paypalService() {
        return new PaypalService("gleides", "123");
    }
}
```
### Injeção de dependência

É uma forma de <b>realizar a inversão de controle</b>: um componente externo instacia a dependência, que é então injetada no objeto "pai" através desse componente. Pode ser implementada de vários formas:
* Container/framework
  * Construtor: injeção obrigatória ao instanciar a classe VendaService
  * Propriedade com anotação @Autowired: injeção privada que torna difícil acesso
  * Método com anotação @Autowired: injeção é feita na chamada do método.

Na clase abaixo usando a notação @Componente do spring, a classe torna-se um bean auto gerenciável pelo framework
```java
@Component
public class VendaService {
  //Injeção de dependência usando propriedade, mas devido ser privada torna-se dificil de ser usado nos testes unitário 
  @Autowired
  private GatewayPagamento gatewayPagamento;

  public VendaService() {

  }

  //Injeção de dependência usando o construtor que obriga a instanciação da interface GatewayPagamento
  public VendaService(GatewayPagamento gatewayPagamento) {
    this.gatewayPagamento = gatewayPagamento;
  }

  //Injeção de dependência usando método
  @Autowired
  public void setGatewayPagamento(GatewayPagamento gatewayPagamento) {
    this.gatewayPagamento = gatewayPagamento;

  }

  public void registrar(Venda venda, String numeroCartao) {
    BigDecimal valorTotal = venda.getPricoUnitario().multiply(new BigDecimal(venda.getQuantidade()));
    System.out.printf("[Venda] Registrando venda de %s no valor total de %f...\n", venda.getProduto(), valorTotal);

        /*Forte acomplamento entre a clase VendaService com a classe PagSeguroService
        PagSeguroService pagSeguroService = new PagSeguroService("857db3dbbce149ab8943430f4d18bdf3");*/

    //Fraco acomplamento usando a interface por meio do construtor ou propriedade ou método
    gatewayPagamento.efetuarPagamento(numeroCartao, valorTotal);
  }
}
```