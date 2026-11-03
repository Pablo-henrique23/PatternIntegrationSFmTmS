package org.example;

// Objeto imutavel armazenado pelo historico.
public record ResultadoAtendimento(
        String tipoAtendimento,
        double valorOriginal,
        double valorAjustado,
        double valorCobrado) {
}
