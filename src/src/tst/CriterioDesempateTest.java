package tst;

import static org.junit.Assert.*;

import model.Partida;
import model.TabelaClassificacao;
import model.Time;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class CriterioDesempateTest {

    private TimeStats[] dadosTimes;
    private String[] ordemEsperada;
    private String criterioDesempate;

    public static class TimeStats {
        String nome;
        int[][] resultados;

        public TimeStats(String nome, int[][] resultados) {
            this.nome = nome;
            this.resultados = resultados;
        }
    }

    @Parameters(name = "{2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {
                        new TimeStats[] {
                                new TimeStats("Flamengo", new int[][]{{3,0}, {2,0}}),
                                new TimeStats("Palmeiras", new int[][]{{1,1}, {1,1}, {3,0}})
                        },
                        new String[]{"Flamengo", "Palmeiras"},
                        "Desempate por pontos"
                },
                {
                        new TimeStats[] {
                                new TimeStats("São Paulo", new int[][]{{3,0}, {0,1}}),
                                new TimeStats("Corinthians", new int[][]{{1,1}, {1,1}, {1,1}})
                        },
                        new String[]{"São Paulo", "Corinthians"},
                        "Desempate por vitórias"
                },
                {
                        new TimeStats[] {
                                new TimeStats("Atlético-MG", new int[][]{{4,0}}),
                                new TimeStats("Grêmio", new int[][]{{2,0}})
                        },
                        new String[]{"Atlético-MG", "Grêmio"},
                        "Desempate por saldo de gols"
                },
                // Cenário 4: Desempate por gols marcados
                {
                        new TimeStats[] {
                                new TimeStats("Internacional", new int[][]{{3,1}}),
                                new TimeStats("Fluminense", new int[][]{{2,0}})
                        },
                        new String[]{"Internacional", "Fluminense"},
                        "Desempate por gols marcados"
                },
                // Cenário 5: Três times com critérios múltiplos
                {
                        new TimeStats[] {
                                new TimeStats("Botafogo", new int[][]{{3,0}, {2,1}}),
                                new TimeStats("Santos", new int[][]{{2,0}, {1,0}}),
                                new TimeStats("Vasco", new int[][]{{1,1}, {1,1}, {3,0}})
                        },
                        new String[]{"Botafogo", "Santos", "Vasco"},
                        "Três times - critérios combinados"
                },
                {
                        new TimeStats[] {
                                new TimeStats("Cruzeiro", new int[][]{{2,0}, {3,1}, {1,1}}),
                                new TimeStats("Bahia", new int[][]{{1,0}, {2,1}, {0,2}}),
                                new TimeStats("Fortaleza", new int[][]{{1,1}, {2,2}, {3,0}})
                        },
                        new String[]{"Cruzeiro", "Bahia", "Fortaleza"},
                        "Classificação por pontos diferentes"
                },
                {
                        new TimeStats[] {
                                new TimeStats("Bragantino", new int[][]{{0,1}, {1,2}}),
                                new TimeStats("Goiás", new int[][]{{0,2}, {1,3}})
                        },
                        new String[]{"Bragantino", "Goiás"},
                        "Desempate com saldos negativos"
                },
                {
                        new TimeStats[] {
                                new TimeStats("Cuiabá", new int[][]{{3,2}}),
                                new TimeStats("Coritiba", new int[][]{{2,1}}),
                                new TimeStats("América-MG", new int[][]{{1,0}})
                        },
                        new String[]{"Cuiabá", "Coritiba", "América-MG"},
                        "Desempate final por gols marcados"
                }
        });
    }

    public CriterioDesempateTest(TimeStats[] dadosTimes, String[] ordemEsperada,
                                 String criterioDesempate) {
        this.dadosTimes = dadosTimes;
        this.ordemEsperada = ordemEsperada;
        this.criterioDesempate = criterioDesempate;
    }

    @Test
    public void testOrdenacaoPorCriterios() {
        List<Time> times = new ArrayList<>();

        for (TimeStats stats : dadosTimes) {
            Time time = new Time(stats.nome);
            Time adversario = new Time("Adversário");

            for (int[] resultado : stats.resultados) {
                Partida partida = new Partida(time, adversario, resultado[0], resultado[1]);
                time.processarResultado(partida);
            }

            times.add(time);
        }

        TabelaClassificacao tabela = new TabelaClassificacao();
        tabela.ordenar(times);

        for (int i = 0; i < ordemEsperada.length; i++) {
            assertEquals(criterioDesempate + " - Posição " + (i+1),
                    ordemEsperada[i], times.get(i).getNome());
        }
    }
}