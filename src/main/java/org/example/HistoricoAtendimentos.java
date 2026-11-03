package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Singleton: uma unica instancia compartilha os registros do sistema.
public final class HistoricoAtendimentos {
    private static final HistoricoAtendimentos INSTANCIA = new HistoricoAtendimentos();
    private final List<ResultadoAtendimento> registros = new ArrayList<>();

    private HistoricoAtendimentos() {
    }

    public static HistoricoAtendimentos getInstancia() {
        return INSTANCIA;
    }

    public synchronized void registrar(ResultadoAtendimento resultado) {
        registros.add(Objects.requireNonNull(resultado, "Resultado obrigatorio"));
    }

    public synchronized List<ResultadoAtendimento> listar() {
        return List.copyOf(registros);
    }

    // Util em testes e ao reiniciar uma demonstracao.
    public synchronized void limpar() {
        registros.clear();
    }
}
