package to.mattias;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import to.mattias.entities.Customer;
import to.mattias.repositories.CustomerRepository;

import javax.transaction.Transactional;

import static org.junit.Assert.assertTrue;

/**
 * <h1>Created by Mattias on 2017-01-31.</h1>
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
@Transactional
@Rollback(false)
public class CustomerTest {

    @Autowired
    private CustomerRepository customerRepository;

    private static boolean init = false;

    @Before
    public void initCustomerTest() {
        if(!init) {
            Customer customer = new Customer();
            customer.setCustomerName("DefaultCustomer");
            customerRepository.save(customer);
        }
    }

    @Test
    public void testToChangeCustomerName() {
        // Fetch customer from db
        Customer customer = customerRepository.findByCustomerName("DefaultCustomer");

        // Change the customer's name and persist it
        customer.setCustomerName("TestCustomer");
        customerRepository.save(customer);

        // Fetch all customers from the db and assert that the first one is called 'TestCustomer'
        assertTrue("Couldn't change customer name",
                customerRepository.findAll().get(0).getCustomerName().equals("TestCustomer"));
    }
}
