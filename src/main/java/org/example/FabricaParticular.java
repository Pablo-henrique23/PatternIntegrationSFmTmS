package org.example;

public class FabricaParticular extends FabricaCobranca {
    @Override
    protected EstrategiaCobranca criarEstrategia() {
        return new CobrancaParticular();
    }
}
