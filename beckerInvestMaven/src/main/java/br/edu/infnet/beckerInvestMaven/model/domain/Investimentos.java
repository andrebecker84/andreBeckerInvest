package br.edu.infnet.beckerInvestMaven.model.domain;

public class Investimentos {
    private final String nome;
    private final double montanteInvestido;
    private final double taxaRetornoAnual;
    private final int periodoInvestimentoMeses;

    public Investimentos(String nome, double montanteInvestido, double taxaRetornoAnual, int periodoInvestimentoMeses) {
        if (montanteInvestido < 0) {
            throw new IllegalArgumentException("Montante investido não pode ser negativo");
        }
        if (taxaRetornoAnual < 0) {
            throw new IllegalArgumentException("Taxa de retorno anual não pode ser negativa");
        }
        if (periodoInvestimentoMeses < 0) {
            throw new IllegalArgumentException("Período de investimento em meses não pode ser negativo");
        }

        this.nome = nome;
        this.montanteInvestido = montanteInvestido;
        this.taxaRetornoAnual = taxaRetornoAnual;
        this.periodoInvestimentoMeses = periodoInvestimentoMeses;
    }

    public String getNome() {
        return nome;
    }

    public double getMontanteInvestido() {
        return montanteInvestido;
    }

    public double getTaxaRetornoAnual() {
        return taxaRetornoAnual;
    }

    public int getPeriodoInvestimentoMeses() {
        return periodoInvestimentoMeses;
    }

    public double calcularRetornoTotal() {
        double retornoTotal = montanteInvestido * Math.pow(1 + (taxaRetornoAnual / 100), periodoInvestimentoMeses / 12.0);
        retornoTotal = Math.round(retornoTotal * 100.0) / 100.0;
        return retornoTotal;
    }

    public boolean isInvestimentoLucrativo() {
        return calcularRetornoTotal() > 0;
    }
}