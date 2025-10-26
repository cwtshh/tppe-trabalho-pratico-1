package tst;

import Exceptions.QtdPartidasInvalida;
import Exceptions.QtdTimesInvalida;
import model.Campeonato;
import model.Time;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class CampeonatoExceptionTest {
    private final List<Time> times;

    public CampeonatoExceptionTest(List<Time> times) {
        this.times = times;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getParameters() {
        List<Time> timesInvalidos1 = new ArrayList<>();
        for (int i = 0; i < 19; i++) {
            timesInvalidos1.add(new Time("Time " + i));
        }

        List<Time> timesInvalidos2 = new ArrayList<>();
        for (int i = 0; i < 21; i++) {
            timesInvalidos2.add(new Time("Time " + i));
        }

        return List.of(
                new Object[] { timesInvalidos1 },
                new Object[] { timesInvalidos2 }
        );
    }

    @Test(expected = QtdTimesInvalida.class)
    @Category(Excecao.class)
    public void testGerarTurnoException() throws QtdPartidasInvalida, QtdTimesInvalida {
        Campeonato campeonato = new Campeonato(times);
        campeonato.sortearRodadas();
    }
}
