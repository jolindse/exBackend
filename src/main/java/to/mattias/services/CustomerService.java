package to.mattias.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import to.mattias.entities.Customer;
import to.mattias.repositories.CustomerRepository;

import java.util.List;

/**
 * <h1>Created by Mattias on 2017-01-23.</h1>
 */
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repo;

    public List<Customer> findAll() {
        return repo.findAll();
    }

    public Customer findById(int customerId) {
        return repo.findOne(customerId);
    }


    public Customer save(Customer customer) {
        return repo.saveAndFlush(customer);
    }

    public void delete(int customerId) {
        repo.delete(customerId);
    }

    public Customer update(Customer customer) {
        return save(customer);
    }

}
