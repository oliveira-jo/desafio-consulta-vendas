
## DESAFIO: Consulta vendas

#### Este é um desafio do módulo JPA, consultas SQL e JPQL do curso Java Spring Professional - devsuperior.

Trata-se de um sistema de vendas (Sale) e vendedores (Seller). Cada venda está para um vendedor, e um vendedor pode ter várias vendas.

![Class](/src/main/resources/img/Class.png)

## Você deverá implementar as seguintes consultas

##  * Relatório de vendas
1. [IN] O usuário informa, opcionalmente, data inicial, data final e um trecho do nome do vendedor. 
2. [OUT] O sistema informa uma listagem paginada contendo id, data, quantia vendida e nome do vendedor, das vendas que se enquadrem nos dados informados.

#### Informações complementares:
* Se a data final não for informada, considerar a data atual do sistema. Para instanciar a data atual,
utilize o comando:
````
  LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
````
* Se a data inicial não for informada, considerar a data de 1 ano antes da data final. Para instanciar uma data com um ano a menos, use a função minusYears:
````
  LocalDate result = minhaData.minusYears(1L);
````
* Se o nome não for informado, considerar o texto vazio.
* Dica: receba todos os dados como String no controller, e faça os tratamentos das datas acima, instanciando os objetos LocalDate, no service.

## * Sumário de vendas por vendedor
1. [IN] O usuário informa, opcionalmente, data inicial, data final.
2. [OUT] O sistema informa uma listagem contendo nome do vendedor e soma de vendas deste vendedor no período informado.

#### Informações complementares:
* As mesmas do caso de uso Relatório de vendas

## Endpoints

### Busca venda por id
```http
  GET http://localhost:8080/sales/{id}
```
| PathVariable   | Tipo       |Descrição|
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `Long` | **Obrigatório**. ID da venda |

### Busca relatório (Paginada)
```http
http://localhost:8080/sales/report
```
| RequestParam   | Tipo       |Descrição|
| :---------- | :--------- | :------------------------------------------ |
| `minDate`      | `String` | **Opcional**. Data do início da busca |
| `maxDate`      | `String` | **Opcional**. Data do término da busca |
| `name`      | `String` | **Opcional**. Nome do vendedor |

### Busca sumário
```http
http://localhost:8080/sales/summary
```
| RequestParam   | Tipo       |Descrição|
| :---------- | :--------- | :------------------------------------------ |
| `minDate`      | `String` | **Opcional**. Data do início da busca |
| `maxDate`      | `String` | **Opcional**. Data do término da busca |