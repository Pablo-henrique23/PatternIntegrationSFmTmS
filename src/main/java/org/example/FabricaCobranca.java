package org.example;

// Creator do Factory Method.
public abstract class FabricaCobranca {
    // Factory Method: cada subclasse decide qual estrategia instanciar.
    protected abstract EstrategiaCobranca criarEstrategia();

    public final FaturamentoHospitalar criarFaturamento() {
        return new FaturamentoHospitalar(criarEstrategia());
    }
}
