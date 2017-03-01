package to.mattias;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

/**
 * <h1>Created by Mattias on 2017-03-01.</h1>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
@Transactional
@Rollback(false)
public class FileuploadTest {

    @Test
    public void testToUploadAFile() {

    }
}
