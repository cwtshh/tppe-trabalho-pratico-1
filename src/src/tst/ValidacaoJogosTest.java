package tst;

import Exceptions.QtdPartidasInvalida;
import Exceptions.QtdTimesInvalida;
import model.Campeonato;
import model.Partida;
import model.Rodada;
import model.Time;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.*;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class ValidacaoJogosTest {
    private Campeonato campeonato;
    private List<Time> times;
    private int confrontosPorDupla;

    @Parameters
    public static Collection<Object[]> getParameters() {
        Object[][] parametros = new Object[][] {
                { 2 },
                { 2 },
                { 2 }
        };
        return Arrays.asList(parametros);
    }

    public ValidacaoJogosTest(int confrontosPorDupla) {
        this.confrontosPorDupla = confrontosPorDupla;
    }

    @Before
    public void setup() throws QtdTimesInvalida, QtdPartidasInvalida {
        times = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            times.add(new Time("Time " + i));
        }
        campeonato = new Campeonato(times);
        campeonato.sortearRodadas();
    }

    @Test
    @Category(Funcional.class)
    public void testNaoExistemJogosDuplicados() {
       List<Rodada> rodadas = campeonato.getRodadas();
       Set<String> confrontos = new HashSet<>();

       for(Rodada rodada : rodadas) {
           for (Partida partida : rodada.getPartidas()) {
               String confronto = partida.getMandante().getNome() +
                       " vs " +
                       partida.getVisitante().getNome();

               assertFalse("Confronto duplicado encontrado: " + confronto, confrontos.contains(confronto));
               confrontos.add(confronto);
           }
       }
    }

    @Test
    @Category(Funcional.class)
    public void testCadaConfrontoAconteceNumeroCentroDeVezes() {
        List<Rodada> rodadas = campeonato.getRodadas();

        for(int i = 0; i < times.size(); i++) {
            for(int j = i + 1; j < times.size(); j++) {
                Time timeA = times.get(i);
                Time timeB = times.get(j);

                int confrontosEncontrados = 0;

                for(Rodada rodada: rodadas) {
                    for(Partida partida : rodada.getPartidas()) {
                        String mandante = partida.getMandante().getNome();
                        String visitante = partida.getVisitante().getNome();

                        if ((mandante.equals(timeA.getNome()) &&
                                visitante.equals(timeB.getNome())) ||
                                (mandante.equals(timeB.getNome()) &&
                                        visitante.equals(timeA.getNome()))) {
                            confrontosEncontrados++;
                        }
                    }
                }
                assertEquals(confrontosPorDupla, confrontosEncontrados);
            }
        }
    }

    @Test
    @Category(Funcional.class)
    public void testTimeNaoJogaContraSiMesmo() {
        List<Rodada> rodadas = campeonato.getRodadas();

        for(Rodada rodada : rodadas) {
            for(Partida partida : rodada.getPartidas()) {
                assertNotEquals(partida.getMandante().getNome(), partida.getVisitante().getNome());
            }
        }
    }

    @Test
    @Category(Funcional.class)
    public void testTotalDePartidasCorreto() {
        List<Rodada> rodadas = campeonato.getRodadas();
        int totalPartidas = 0;

        for (Rodada rodada : rodadas) {
            totalPartidas += rodada.getPartidas().size();
        }

        assertEquals(380, totalPartidas);
    }

    @Test
    @Category(Funcional.class)
    public void testConfrontoInversoOcorre() {
        List<Rodada> rodadas = campeonato.getRodadas();

        // Testa para cada dupla de times
        for(int i = 0; i < times.size(); i++) {
            for(int j = i + 1; j < times.size(); j++) {
                Time timeA = times.get(i);
                Time timeB = times.get(j);

                boolean encontrouAvsB = false;
                boolean encontrouBvsA = false;

                for (Rodada rodada : rodadas) {
                    for (Partida partida : rodada.getPartidas()) {
                        if (partida.getMandante().getNome().equals(timeA.getNome()) &&
                                partida.getVisitante().getNome().equals(timeB.getNome())) {
                            encontrouAvsB = true;
                        }
                        if (partida.getMandante().getNome().equals(timeB.getNome()) &&
                                partida.getVisitante().getNome().equals(timeA.getNome())) {
                            encontrouBvsA = true;
                        }
                    }
                }

                assertTrue(String.format("%s vs %s não encontrado",
                        timeA.getNome(), timeB.getNome()), encontrouAvsB);
                assertTrue(String.format("%s vs %s não encontrado",
                        timeB.getNome(), timeA.getNome()), encontrouBvsA);
            }
        }
    }
}
