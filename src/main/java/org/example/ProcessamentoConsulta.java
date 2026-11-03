package org.example;

public class ProcessamentoConsulta extends ProcessamentoAtendimento {
    public ProcessamentoConsulta(FabricaCobranca fabrica) {
        super(fabrica);
    }

    @Override
    protected double ajustarValor(double valorBase) {
        return valorBase;
    }

    @Override
    protected String tipoAtendimento() {
        return "Consulta";
    }
}
