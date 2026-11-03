package org.example;

public class FabricaConvenio extends FabricaCobranca {
    @Override
    protected EstrategiaCobranca criarEstrategia() {
        return new CobrancaConvenio();
    }
}
