package org.example;

public abstract class FabricaCobranca {
    protected abstract EstrategiaCobranca criarEstrategia();

    public final FaturamentoHospitalar criarFaturamento() {
        return new FaturamentoHospitalar(criarEstrategia());
    }
}
