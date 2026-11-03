package org.example;

public class CobrancaParticular implements EstrategiaCobranca {
    @Override
    public double calcularValor(double valorBase) {
        return valorBase;
    }
}
