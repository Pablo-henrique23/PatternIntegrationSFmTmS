package org.example;

import java.util.Objects;

public abstract class ProcessamentoAtendimento {
    private final FabricaCobranca fabrica;

    protected ProcessamentoAtendimento(FabricaCobranca fabrica) {
        this.fabrica = Objects.requireNonNull(fabrica, "Fabrica obrigatoria");
    }

    public final ResultadoAtendimento processar(double valorBase) {
        validarValor(valorBase);
        double valorAjustado = ajustarValor(valorBase);
        FaturamentoHospitalar faturamento = fabrica.criarFaturamento();
        double valorCobrado = faturamento.calcularValor(valorAjustado);

        ResultadoAtendimento resultado = new ResultadoAtendimento(
                tipoAtendimento(), valorBase, valorAjustado, valorCobrado);
        HistoricoAtendimentos.getInstancia().registrar(resultado);
        return resultado;
    }

    private void validarValor(double valorBase) {
        if (!Double.isFinite(valorBase) || valorBase < 0) {
            throw new IllegalArgumentException("Valor base invalido");
        }
    }

    protected abstract double ajustarValor(double valorBase);

    protected abstract String tipoAtendimento();
}
