package tst;

import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Category(Funcional.class)
@Suite.SuiteClasses({
        SorteioRodadaTest.class,
        ValidacaoJogosTest.class,
        CalculoPontuacaoTest.class,
        CalculoEstatisticasTest.class,
        CriterioDesempateTest.class
})
public class FuncionalTests {
}
