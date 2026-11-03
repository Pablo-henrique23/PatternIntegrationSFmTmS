package org.example;

public class FabricaSUS extends FabricaCobranca {
    @Override
    protected EstrategiaCobranca criarEstrategia() {
        return new CobrancaSUS();
    }
}
