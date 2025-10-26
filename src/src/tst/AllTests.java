package tst;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        FuncionalTests.class,
        ExceptionTests.class
})
public class AllTests {

}