package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IntegracaoPadroesTest {

    @BeforeEach
    void limparHistorico() {
        HistoricoAtendimentos.getInstancia().limpar();
    }

    @Test
    void estrategiaParticularCobraValorIntegral() {
        FaturamentoHospitalar faturamento = new FabricaParticular().criarFaturamento();
        assertEquals(200.0, faturamento.calcularValor(200.0), 0.001);
    }

    @Test
    void estrategiaConvenioCobraVintePorCento() {
        FaturamentoHospitalar faturamento = new FabricaConvenio().criarFaturamento();
        assertEquals(40.0, faturamento.calcularValor(200.0), 0.001);
    }

    @Test
    void estrategiaSUSNaoCobraDiretamenteDoPaciente() {
        FaturamentoHospitalar faturamento = new FabricaSUS().criarFaturamento();
        assertEquals(0.0, faturamento.calcularValor(200.0), 0.001);
    }

    @Test
    void strategyPermiteTrocarEstrategiaEmExecucao() {
        FaturamentoHospitalar faturamento = new FabricaParticular().criarFaturamento();
        assertEquals(200.0, faturamento.calcularValor(200.0), 0.001);
        faturamento.definirEstrategia(new CobrancaConvenio());
        assertEquals(40.0, faturamento.calcularValor(200.0), 0.001);
    }

    @Test
    void templateProcessaConsultaComConvenioERegistra() {
        ProcessamentoAtendimento processamento = new ProcessamentoConsulta(new FabricaConvenio());
        ResultadoAtendimento resultado = processamento.processar(200.0);

        assertEquals("Consulta", resultado.tipoAtendimento());
        assertEquals(200.0, resultado.valorOriginal(), 0.001);
        assertEquals(200.0, resultado.valorAjustado(), 0.001);
        assertEquals(40.0, resultado.valorCobrado(), 0.001);
        assertEquals(List.of(resultado), HistoricoAtendimentos.getInstancia().listar());
    }

    @Test
    void templateAplicaAdicionalDeUrgenciaAntesDaCobrancaParticular() {
        ProcessamentoAtendimento processamento = new ProcessamentoUrgencia(new FabricaParticular());
        ResultadoAtendimento resultado = processamento.processar(200.0);

        assertEquals("Urgencia", resultado.tipoAtendimento());
        assertEquals(300.0, resultado.valorAjustado(), 0.001);
        assertEquals(300.0, resultado.valorCobrado(), 0.001);
    }

    @Test
    void integraUrgenciaEConvenio() {
        ProcessamentoAtendimento processamento = new ProcessamentoUrgencia(new FabricaConvenio());
        assertEquals(60.0, processamento.processar(200.0).valorCobrado(), 0.001);
    }

    @Test
    void integraUrgenciaESUS() {
        ProcessamentoAtendimento processamento = new ProcessamentoUrgencia(new FabricaSUS());
        assertEquals(0.0, processamento.processar(200.0).valorCobrado(), 0.001);
    }

    @Test
    void singletonSempreRetornaMesmaInstancia() {
        assertSame(HistoricoAtendimentos.getInstancia(), HistoricoAtendimentos.getInstancia());
    }

    @Test
    void singletonReuneRegistrosDeProcessamentosDiferentes() {
        new ProcessamentoConsulta(new FabricaParticular()).processar(100.0);
        new ProcessamentoUrgencia(new FabricaSUS()).processar(200.0);

        assertEquals(2, HistoricoAtendimentos.getInstancia().listar().size());
    }

    @Test
    void historicoNaoPermiteAlteracaoExterna() {
        new ProcessamentoConsulta(new FabricaParticular()).processar(100.0);
        List<ResultadoAtendimento> copia = HistoricoAtendimentos.getInstancia().listar();
        assertThrows(UnsupportedOperationException.class, copia::clear);
    }

    @Test
    void valoresInvalidosSaoRejeitadosSemRegistrar() {
        ProcessamentoAtendimento processamento = new ProcessamentoConsulta(new FabricaParticular());
        assertThrows(IllegalArgumentException.class, () -> processamento.processar(-10.0));
        assertThrows(IllegalArgumentException.class, () -> processamento.processar(Double.NaN));
        assertThrows(IllegalArgumentException.class, () -> processamento.processar(Double.POSITIVE_INFINITY));
        assertTrue(HistoricoAtendimentos.getInstancia().listar().isEmpty());
    }

    @Test
    void dependenciasNulasSaoRejeitadas() {
        assertThrows(NullPointerException.class, () -> new ProcessamentoConsulta(null));
        assertThrows(NullPointerException.class, () -> new FaturamentoHospitalar(null));
    }
}
