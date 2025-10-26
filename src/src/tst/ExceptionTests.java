package tst;

import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Category(Excecao.class)
@Suite.SuiteClasses({
        CampeonatoExceptionTest.class,
        CampeonatoConstrutorExceptionTest.class,
        CampeonatoSortearRodadasExceptionTest.class
})
public class ExceptionTests {
}
