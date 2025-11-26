package tst;

import model.TabelaClassificacao;
import model.Time;
import model.EstatisticasTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class ExibirTabelaClassificacaoTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void tearDown() {
        System.setOut(originalOut);
    }

    /** Ajustado para escrever dentro de Time.estatisticas */
    private void setCampo(Time time, String campo, int valor) {
        try {
            Field fEst = Time.class.getDeclaredField("estatisticas");
            fEst.setAccessible(true);
            EstatisticasTime estat = (EstatisticasTime) fEst.get(time);

            Field fInterno = EstatisticasTime.class.getDeclaredField(campo);
            fInterno.setAccessible(true);
            fInterno.setInt(estat, valor);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testExibirClassificacao() {
        Time t1 = new Time("Flamengo");
        Time t2 = new Time("Palmeiras");
        Time t3 = new Time("Botafogo");

        setCampo(t1, "pontos", 10);
        setCampo(t1, "vitorias", 4);
        setCampo(t1, "empates", 3);
        setCampo(t1, "derrotas", 1);
        setCampo(t1, "golsMarcados", 8);
        setCampo(t1, "golsSofridos", 3);

        setCampo(t2, "pontos", 12);
        setCampo(t2, "vitorias", 4);
        setCampo(t2, "empates", 4);
        setCampo(t2, "derrotas", 0);
        setCampo(t2, "golsMarcados", 7);
        setCampo(t2, "golsSofridos", 1);

        setCampo(t3, "pontos", 7);
        setCampo(t3, "vitorias", 2);
        setCampo(t3, "empates", 1);
        setCampo(t3, "derrotas", 1);
        setCampo(t3, "golsMarcados", 5);
        setCampo(t3, "golsSofridos", 4);

        List<Time> times = Arrays.asList(t1, t2, t3);

        TabelaClassificacao tabela = new TabelaClassificacao();
        tabela.exibirClassificacao(times);

        String saida = outContent.toString();

        assertTrue("Deve imprimir cabeçalho",
                saida.contains("TABELA DE CLASSIFICAÇÃO"));

        assertTrue("Deve conter Palmeiras", saida.contains("Palmeiras"));
        assertTrue("Deve conter Flamengo", saida.contains("Flamengo"));

        int indexPalmeiras = saida.indexOf("Palmeiras");
        int indexFlamengo = saida.indexOf("Flamengo");

        assertTrue("Palmeiras deve aparecer antes de Flamengo",
                indexPalmeiras < indexFlamengo);

        assertTrue("Rodapé deve ser impresso",
                saida.contains("Zona de Rebaixamento"));
    }
}
