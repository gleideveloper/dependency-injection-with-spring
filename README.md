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
## Solução: Exemplo do diagrama abaixo fere os dois princípios do SOLID, que são:
