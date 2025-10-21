package tst;

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
    private int partidasPorRodada;


    public SorteioRodadaTest(int numeroTimes, int numeroRodadasEsperado, int partidasPorRodada) {
        this.numeroTimes = numeroTimes;
        this.numeroRodadasEsperado = numeroRodadasEsperado;
        this.partidasPorRodada = partidasPorRodada;
    }

    @Before
    public void setup() throws QtdTimesInvalida {
        times = new ArrayList<>();
        times.add(new Time("Flamengo"));
        times.add(new Time("São Paulo"));
        times.add(new Time("Iderlindos de Uberaba"));
        times.add(new Time("Botafogo"));
        campeonato = new Campeonato(times);
    }

    @Parameters
    public static Collection<Object[]> getParameters() {
        Object[][] parameters = new Object[][] {
                {20, 38, 10},
                {20, 38, 10},
                {20, 38, 10}
        };
        return Arrays.asList(parameters);
    }

    @Test
    @Category(Funcional.class)
    public void testSorteioGerarNumeroCorretoRodadas() {
        campeonato.sortearRodadas();
        List<Rodada> rodadas = campeonato.getRodadas();

        assertEquals(numeroRodadasEsperado, rodadas.size());
    }





}
