package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public synchronized void limpar() {
        registros.clear();
    }
}
