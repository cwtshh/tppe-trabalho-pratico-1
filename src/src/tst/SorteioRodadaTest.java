package tst;

import Exceptions.QtdPartidasInvalida;
import Exceptions.QtdTimesInvalida;
import org.junit.experimental.categories.Category;
import model.Campeonato;
import model.Rodada;
import model.Time;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.*;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class SorteioRodadaTest {
    private Campeonato campeonato;
    private List<Time> times;
    private int numeroRodadasEsperado;
    private int numeroTimes;


    public SorteioRodadaTest(int numeroTimes, int numeroRodadasEsperado) {
        this.numeroTimes = numeroTimes;
        this.numeroRodadasEsperado = numeroRodadasEsperado;
    }

    @Before
    public void setup() throws QtdTimesInvalida {
        times = new ArrayList<>();
        for (int i = 1; i <= numeroTimes; i++) {
            times.add(new Time("Time " + i));
        }
        campeonato = new Campeonato(times);
    }

    @Parameters
    public static Collection<Object[]> getParameters() {
        Object[][] parameters = new Object[][] {
                {20, 38},
                {20, 38},
                {20, 38}
        };
        return Arrays.asList(parameters);
    }

    @Test
    @Category(Funcional.class)
    public void testSorteioGerarNumeroCorretoRodadas() throws QtdPartidasInvalida {
        campeonato.sortearRodadas();
        List<Rodada> rodadas = campeonato.getRodadas();
        assertEquals(numeroRodadasEsperado, rodadas.size());
    }
}
