package br.edu.infnet.beckerInvestMaven;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.infnet.beckerInvestMaven.model.domain.Investimentos;

@SpringBootTest
class InvestimentosTests {
    
    private List<Investimentos> investimentos;
    
    @BeforeEach
    void setUp() {
        double valorInvestimento = 10000;

        investimentos = new ArrayList<>();
        investimentos.add(new Investimentos("Ações Becker Java", valorInvestimento, 8, 24));
        investimentos.add(new Investimentos("Fundos Imobiliários", valorInvestimento, 6, 36));
        investimentos.add(new Investimentos("Tesouro Direto", valorInvestimento, 5, 12));
        investimentos.add(new Investimentos("Outra Opção", valorInvestimento, 10, 48));

        investimentos.forEach(Investimentos::calcularRetornoTotal);

        investimentos.sort(Comparator.comparingDouble(Investimentos::calcularRetornoTotal).reversed());
    }
        
    @Test
    void testComparativoInvestimentos() {
        // Determinando o melhor investimento
        Investimentos melhorInvestimento = investimentos.get(0);

        System.out.println("O melhor investimento é: " + melhorInvestimento.getNome());

        assertEquals("Outra Opção", melhorInvestimento.getNome());
        assertEquals(14641.0, melhorInvestimento.calcularRetornoTotal(), 0.01);
    }

    @Test
    void testInvestimentoLucrativo_RetornoPositivo() {
        Investimentos investimentoLucrativo = new Investimentos("Investimento Lucrativo", 10000, 10, 12);
        assertTrue(investimentoLucrativo.isInvestimentoLucrativo());
    }

    @Test
    void testInvestimentoLucrativo_RetornoZero() {
        Investimentos investimentoZero = new Investimentos("Investimento Zero", 0, 5, 12);
        assertFalse(investimentoZero.isInvestimentoLucrativo());
    }

    @Test
    void testInvestimentoLucrativo_RetornoNegativo() {
        // Investimento com retorno total negativo não é possível devido à validação de taxa de retorno anual
        assertThrows(IllegalArgumentException.class, () -> new Investimentos("Investimento Prejuizo", -10000, 5, 12));
    }

    @Test
    void testNomeInvestimento() {
        for (Investimentos investimento : investimentos) {
            assertNotEquals("", investimento.getNome());
        }
    }

    @Test
    void testMontanteInvestimentoPositivo() {
        assertThrows(IllegalArgumentException.class, () -> new Investimentos("Investimento Zero", -100, 5, 12));
    }

    @Test
    void testTaxaRetornoAnualPositiva() {
        assertThrows(IllegalArgumentException.class, () -> new Investimentos("Investimento Zero", 10000, -5, 12));
    }

    @Test
    void testPeriodoInvestimentoMesesPositivo() {
        assertThrows(IllegalArgumentException.class, () -> new Investimentos("Investimento Zero", 10000, 5, -12));
    }

    @Test
    void testGetMontanteInvestido() {
        Investimentos investimento = new Investimentos("Teste", 10000, 5, 12);
        assertEquals(10000, investimento.getMontanteInvestido());
    }

    @Test
    void testGetTaxaRetornoAnual() {
        Investimentos investimento = new Investimentos("Teste", 10000, 5, 12);
        assertEquals(5, investimento.getTaxaRetornoAnual());
    }

    @Test
    void testGetPeriodoInvestimentoMeses() {
        Investimentos investimento = new Investimentos("Teste", 10000, 5, 12);
        assertEquals(12, investimento.getPeriodoInvestimentoMeses());
    }
}