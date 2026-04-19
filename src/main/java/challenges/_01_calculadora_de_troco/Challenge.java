package challenges._01_calculadora_de_troco;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Challenge {

    // Parte do pressuposto que está ordenado (decrescecnte)
    private static final BigDecimal[] CEDULAS = {new BigDecimal(100), new BigDecimal(50), new BigDecimal(20), new BigDecimal(10), new BigDecimal(5), new BigDecimal(2), new BigDecimal(1), new BigDecimal("0.50"), new BigDecimal("0.25"), new BigDecimal("0.10"), new BigDecimal("0.05"), new BigDecimal("0.01")};

    static void main(String[] args) {
        calcularTroco(BigDecimal.valueOf(37.45), BigDecimal.valueOf(50.00));
    }

    public static Map<BigDecimal, Integer> calcularTroco(BigDecimal valorCompra, BigDecimal valorPago) {
        if(valorCompra.compareTo(valorPago) == 0) {
            System.out.println("Não há troco necessário.");
            return Map.of();
        }

        BigDecimal valorPendente = valorPago.subtract(valorCompra);

        if(valorPendente.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("O valor pago não pode ser menor que o valor da compra!");
        }

        Map<BigDecimal, Integer> result = new HashMap<>();

        int i = 0;
        while(valorPendente.compareTo(BigDecimal.ZERO) > 0 && i < CEDULAS.length) {
            BigDecimal cedula = CEDULAS[i];

            if(cedula.compareTo(valorPendente) <= 0) {
                valorPendente = valorPendente.subtract(cedula);
                result.put(cedula, result.getOrDefault(cedula, 0) + 1);
                continue;
            }

            i++;
        }

        System.out.println(result);
        return result;
    }
}
