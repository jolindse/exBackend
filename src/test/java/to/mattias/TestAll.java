package to.mattias;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import to.mattias.CustomerTest;
import to.mattias.ProjectTest;
import to.mattias.SprintTest;
import to.mattias.TaskTest;

/**
 * Created by juan on 2017-02-15.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CustomerTest.class,
        ProjectTest.class,
        SprintTest.class,
        TaskTest.class
})
public class TestAll {

    public TestAll() {
    }
}
