package tst;

import Exceptions.QtdPartidasInvalida;
import model.Partida;
import model.Rodada;
import model.Time;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class RodadaExceptionTest {
    Rodada rodada;
    Partida partida;

    public RodadaExceptionTest(Rodada rodada, Partida partida) {
        this.rodada = rodada;
        this.partida = partida;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getParameters() throws QtdPartidasInvalida {
        Rodada rodadaComLimite = new Rodada(1);
        Time t1 = new Time("A");
        Time t2 = new Time("B");
        Partida partidaSimples = new Partida(t1, t2, 0, 0);

        for (int i = 0; i < 10; i++) {
            rodadaComLimite.adicionarPartida(new Partida(t1, t2, 1, 1));
        }

        return Arrays.asList(new Object[][] {
                { rodadaComLimite, partidaSimples }
        });
    }

    @Test(expected = QtdPartidasInvalida.class)
    @Category(Excecao.class)
    public void testAdicionarPartidaExcecao() throws QtdPartidasInvalida {
        rodada.adicionarPartida(partida);
    }
}
