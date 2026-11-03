package org.example;

public class CobrancaConvenio implements EstrategiaCobranca {
    @Override
    public double calcularValor(double valorBase) {
        return valorBase * 0.20;
    }
}
