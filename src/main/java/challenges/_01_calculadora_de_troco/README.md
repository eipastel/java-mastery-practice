# Calculadora de Troco

Dado o valor de uma compra e o valor pago pelo cliente, calcule o troco mínimo usando as cédulas e moedas disponíveis no Brasil.

## O que implementar

- Método `calcularTroco(double valorCompra, double valorPago)` que retorna um `Map<Double, Integer>` onde a chave é o valor da cédula/moeda e o valor é a quantidade usada
- Use as denominações: 100, 50, 20, 10, 5, 2, 1, 0.50, 0.25, 0.10, 0.05, 0.01
- Minimize a quantidade total de cédulas e moedas devolvidas
- Lance `IllegalArgumentException` se o valor pago for menor que o valor da compra

## Exemplo

```
calcularTroco(37.45, 50.00)
→ troco de 12.55
→ {10.0=1, 2.0=1, 0.50=1, 0.05=1}

calcularTroco(100.00, 100.00)
→ {} (troco zero, mapa vazio)

calcularTroco(10.00, 5.00)
→ IllegalArgumentException
```

## Bônus (opcional)

- Método `resumoTroco(double valorCompra, double valorPago)` que retorna uma `String` formatada, ex: `"1x R$10,00 | 1x R$2,00 | 1x R$0,50 | 1x R$0,05"`
