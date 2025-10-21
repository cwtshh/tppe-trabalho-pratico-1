package tst;

import model.Partida;
import model.Time;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class CalculoEstatisticasTest {

    private Time time;

    private final List<Partida> partidas;
    private final Estatistica estatistica;
    private final int esperado;

    public CalculoEstatisticasTest(List<Partida> partidas, Estatistica estatistica, int esperado) {
        this.partidas = partidas;
        this.estatistica = estatistica;
        this.esperado = esperado;
    }

    @Before
    public void setUp() {
        time = new Time("Flamengo");
    }

    @Parameterized.Parameters(name = "{index}: {1} esperado: {2}")
    public static Collection<Object[]> data() {
        Time flamengo = new Time("Flamengo");

        return Arrays.asList(new Object[][]{
                {
                        Arrays.asList(
                                new Partida(flamengo, new Time("Palmeiras"), 3, 1),
                                new Partida(flamengo, new Time("São Paulo"), 2, 0),
                                new Partida(flamengo, new Time("Corinthians"), 1, 1)
                        ), Estatistica.VITORIAS, 2
                },
                {
                        Arrays.asList(
                                new Partida(flamengo, new Time("Palmeiras"), 1, 1),
                                new Partida(flamengo, new Time("São Paulo"), 2, 2),
                                new Partida(flamengo, new Time("Corinthians"), 3, 0)
                        ), Estatistica.EMPATES, 2
                },
                {
                        Arrays.asList(
                                new Partida(flamengo, new Time("Palmeiras"), 0, 2),
                                new Partida(flamengo, new Time("São Paulo"), 1, 3),
                                new Partida(flamengo, new Time("Corinthians"), 2, 0)
                        ), Estatistica.DERROTAS, 2
                },
                {
                        Arrays.asList(
                                new Partida(flamengo, new Time("Palmeiras"), 3, 1),
                                new Partida(flamengo, new Time("São Paulo"), 2, 0),
                                new Partida(flamengo, new Time("Corinthians"), 1, 2)
                        ), Estatistica.GOLS_MARCADOS, 6
                },
                {
                        Arrays.asList(
                                new Partida(flamengo, new Time("Palmeiras"), 3, 1),
                                new Partida(flamengo, new Time("São Paulo"), 2, 0),
                                new Partida(flamengo, new Time("Corinthians"), 1, 2)
                        ), Estatistica.GOLS_SOFRIDOS, 3
                },
                {
                        Arrays.asList(
                                new Partida(flamengo, new Time("Palmeiras"), 4, 1),
                                new Partida(flamengo, new Time("São Paulo"), 3, 0)
                        ), Estatistica.SALDO_GOLS, 6
                },
                {
                        Arrays.asList(
                                new Partida(flamengo, new Time("Palmeiras"), 0, 3),
                                new Partida(flamengo, new Time("São Paulo"), 1, 4)
                        ), Estatistica.SALDO_GOLS, -6
                },
                {
                        Arrays.asList(
                                new Partida(flamengo, new Time("Palmeiras"), 2, 1),
                                new Partida(flamengo, new Time("São Paulo"), 0, 1)
                        ), Estatistica.SALDO_GOLS, 0
                },
                {
                        Arrays.asList(
                                new Partida(flamengo, new Time("Palmeiras"), 2, 1),
                                new Partida(flamengo, new Time("São Paulo"), 1, 1),
                                new Partida(flamengo, new Time("Corinthians"), 0, 2)
                        ), Estatistica.JOGOS, 3
                },
        });
    }

    @Test
    public void testEstatisticaCalculadaCorretamente() {
        for (Partida partida : partidas) {
            time.processarResultado(partida);
        }

        int valorAtual;
        switch (estatistica) {
            case VITORIAS:
                valorAtual = time.getVitorias();
                break;
            case EMPATES:
                valorAtual = time.getEmpates();
                break;
            case DERROTAS:
                valorAtual = time.getDerrotas();
                break;
            case GOLS_MARCADOS:
                valorAtual = time.getGolsMarcados();
                break;
            case GOLS_SOFRIDOS:
                valorAtual = time.getGolsSofridos();
                break;
            case SALDO_GOLS:
                valorAtual = time.getSaldoGols();
                break;
            case JOGOS:
                valorAtual = time.getJogos();
                break;
            default:
                throw new IllegalStateException("Estatística não suportada: " + estatistica);
        }

        assertEquals(esperado, valorAtual);
    }

    private enum Estatistica {
        VITORIAS,
        EMPATES,
        DERROTAS,
        GOLS_MARCADOS,
        GOLS_SOFRIDOS,
        SALDO_GOLS,
        JOGOS
    }
}
