package tst;

import model.Partida;
import model.Time;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class CalculoPontuacaoTest {

    private Time timeMandante;
    private Time timeVisitante;

    private final int golsMandante;
    private final int golsVisitante;
    private final String nomeTimeEsperado;
    private final int pontosEsperados;

    public CalculoPontuacaoTest(int golsMandante, int golsVisitante, String nomeTimeEsperado, int pontosEsperados) {
        this.golsMandante = golsMandante;
        this.golsVisitante = golsVisitante;
        this.nomeTimeEsperado = nomeTimeEsperado;
        this.pontosEsperados = pontosEsperados;
    }

    @Before
    public void setup() {
        timeMandante = new Time("Flamengo");
        timeVisitante = new Time("Palmeiras");
    }

    @Parameterized.Parameters(name = "{index}: {2} - {0}x{1} => {3} pontos")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {3, 1, "Flamengo", 3},
                {1, 3, "Palmeiras", 3},
                {2, 2, "Flamengo", 1},
                {2, 2, "Palmeiras", 1},
                {0, 2, "Flamengo", 0},
                {0, 0, "Flamengo", 1},
                {0, 0, "Palmeiras", 1},
                {7, 0, "Flamengo", 3},
        });
    }

    @Test
    @Category(Funcional.class)
    public void testPontuacaoCalculadaCorretamente() {
        Partida partida = new Partida(timeMandante, timeVisitante, golsMandante, golsVisitante);

        timeMandante.processarResultado(partida);
        timeVisitante.processarResultado(partida);

        Time timeVerificado = nomeTimeEsperado.equals("Flamengo") ? timeMandante : timeVisitante;

        assertEquals(pontosEsperados, timeVerificado.getPontos());
    }
}
