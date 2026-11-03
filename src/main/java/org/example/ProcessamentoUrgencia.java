package org.example;

public class ProcessamentoUrgencia extends ProcessamentoAtendimento {
    public ProcessamentoUrgencia(FabricaCobranca fabrica) {
        super(fabrica);
    }

    @Override
    protected double ajustarValor(double valorBase) {
        return valorBase * 1.5;
    }

    @Override
    protected String tipoAtendimento() {
        return "Urgencia";
    }
}
