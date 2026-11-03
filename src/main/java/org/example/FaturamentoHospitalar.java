package org.example;

import java.util.Objects;

// Contexto do Strategy: delega o calculo a uma estrategia intercambiavel.
public class FaturamentoHospitalar {
    private EstrategiaCobranca estrategia;

    public FaturamentoHospitalar(EstrategiaCobranca estrategia) {
        definirEstrategia(estrategia);
    }

    public void definirEstrategia(EstrategiaCobranca estrategia) {
        this.estrategia = Objects.requireNonNull(estrategia, "Estrategia obrigatoria");
    }

    public double calcularValor(double valorBase) {
        if (!Double.isFinite(valorBase) || valorBase < 0) {
            throw new IllegalArgumentException("Valor base invalido");
        }
        return estrategia.calcularValor(valorBase);
    }
}
