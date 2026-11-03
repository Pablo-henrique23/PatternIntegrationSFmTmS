package org.example;

public record ResultadoAtendimento(
        String tipoAtendimento,
        double valorOriginal,
        double valorAjustado,
        double valorCobrado) {
}
